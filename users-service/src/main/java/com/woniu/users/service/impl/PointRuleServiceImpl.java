package com.woniu.users.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.api.outlet.web.entity.PointsInfoToUserCenter;
import com.woniu.api.outlet.web.entity.ProductInfoByOrderCenter;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.users.outlet.dao.mysql.mapper.PointRuleMapper;
import com.woniu.users.outlet.dao.mysql.mapper.UserGrowthLogMapper;
import com.woniu.users.outlet.dao.mysql.mapper.UserMemberInfoMapper;
import com.woniu.users.outlet.dao.mysql.mapper.UserPointLogMapper;
import com.woniu.users.outlet.dao.mysql.po.PointRule;
import com.woniu.users.outlet.dao.mysql.po.UserGrowthLog;
import com.woniu.users.outlet.dao.mysql.po.UserPointLog;
import com.woniu.users.service.IPointRuleService;
import com.woniu.users.util.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PointRuleServiceImpl implements IPointRuleService {

    private final PointRuleMapper pointRuleMapper;

    private final UserMemberInfoMapper userMemberInfoMapper;

    private final UserPointLogMapper userPointLogMapper;

    private final UserMemberInfoServiceImpl userMemberInfoService;


    /**
     *  多条件查询
     * @param pageNo
     * @param pageSize
     * @param isDiscount 是否有折扣
     * @param status 是否使用
     * @return
     */
    @Override
    public PageInfo<PointRule> listPointRule(Long pageNo, Long pageSize, Integer isDiscount, Integer status) {
       Page page = new Page(pageNo,pageSize);
        IPage iPage = pointRuleMapper.listPointRule(page, isDiscount, status);
        PageInfo<PointRule> pageInfo = new PageInfo<>(iPage);
        return pageInfo;
    }

    /**
     *  新增
     * @param pointRule
     * @return
     */
    @Override
    public int addPointRule(PointRule pointRule) {
        // 避免重复新增， 查询是否存在一样的数据
        List<PointRule> pointRules = pointRuleMapper.selectPointRule(pointRule);
        if(pointRules.size() > 0){
            // 重复新增
            return 0;

        }
        pointRule.setPointRuleId(new SnowFlakeGenerator(1,5).nextId());
        pointRule.setStatus(1);
        return pointRuleMapper.addPointRule(pointRule);
    }

    /**
     *  修改
     * @param pointRule
     * @return
     */
    @Override
    public int updatePointRule(PointRule pointRule) {
        PointRule pointRule1 = pointRuleMapper.selectById(pointRule.getPointRuleId());
        if(pointRule1 == null){
            return 0;
        }
        return pointRuleMapper.updatePointRule(pointRule);
    }

    /**
     *  根据id查询
     * @param id
     * @return
     */
    @Override
    public PointRule getPointRuleById(Long id) {
        return pointRuleMapper.getPointRuleById(id);
    }

    /**
     * 接收订单消息转换后的对象
     * @return
     */
    @Override
    public void getOrderMsg(PointsInfoToUserCenter pointsInfoToUserCenter) {
        // 记录总折扣积分数
        Integer deduction = 0;
        // 折扣的总金额
        Double reduceMoney = 0.0;
        for (ProductInfoByOrderCenter p : pointsInfoToUserCenter.getProductList()){
            // 根据skuId到积分规则表中查询对象
            PointRule pointRule = pointRuleMapper.getBySkuId(p.getSkuId());
            // 得到该商品的折扣分
            if(pointRule != null) {
                deduction += pointRule.getDeduction();
                reduceMoney += pointRule.getDeduction()/100.0;
            }
        }
        log.info("总折扣分数:{},总折扣金额：{}",deduction,reduceMoney);
        Long uid = pointsInfoToUserCenter.getMemberId();
        // 根据用户uid查询该用户拥有的积分
        List<Integer> points = userMemberInfoMapper.getPoints(uid);
        // 判断用户积分是否够兑换
        if(points.size()>0 && deduction <= points.get(0)){
            // 积分足够,扣除积分
            //剩余积分
            Integer nowPoint = points.get(0) - deduction;
            userMemberInfoMapper.updateUserMemberPoints(nowPoint, uid, new Date());
            // 新增积分日志记录
            UserPointLog userPointLog = new UserPointLog(null, uid, "订单积分扣除", "订单编号" + pointsInfoToUserCenter.getOrderId(), -deduction, new Date());
            userPointLogMapper.addUserPointLog(userPointLog);

            // 订单产生的积分
            Integer addPoint = pointsInfoToUserCenter.getTotalAmount().intValue();

            // 订单产生的成长值 10元=1成长值
            Integer growth = addPoint/10;

            List<Integer> growthNum = userMemberInfoMapper.getGrowthNum(uid);

            // 更新积分和成长值
           userMemberInfoService.updateUserMemberPoints(uid, nowPoint, nowPoint+addPoint, "订单消费新增");
           userMemberInfoService.updateUserMemberGrowthNum(uid,growthNum.get(0),growthNum.get(0)+growth,"订单消费新增" );
        }
    }



}
