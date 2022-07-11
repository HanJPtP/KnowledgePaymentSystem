package com.woniu.users.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.users.outlet.dao.mysql.mapper.MemberRightsMapper;
import com.woniu.users.outlet.dao.mysql.po.MemberRights;
import com.woniu.users.service.IMemberRightsService;
import com.woniu.users.util.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberRightsServiceImpl implements IMemberRightsService {
    private final MemberRightsMapper memberRightsMapper;

    /**
     *  会员权益条件查询
     * @param pageNo
     * @param pageSize
     * @param rightName
     * @return
     */
    @Override
    public PageInfo<MemberRights> listMemberRights(Long pageNo, Long pageSize, String rightName) {
        Page page = new Page(pageNo,pageSize);
        IPage iPage = memberRightsMapper.listMemberRights(page, rightName);
        PageInfo<MemberRights> pageInfo = new PageInfo<>(iPage);
        return pageInfo;
    }

    /**
     *  新增
     * @param memberRights
     * @return
     */
    @Override
    public int addMemberRights(MemberRights memberRights) {
        // 判断重复新增,根据权益名称查询
        List<MemberRights> memberRights1 = memberRightsMapper.selectByName(memberRights.getRightName());
        if(memberRights1.size() > 0){
            // 新增失败
            return 0;
        }
        memberRights.setStatus(1);
        memberRights.setRightId(new SnowFlakeGenerator(1,5).nextId());
        return memberRightsMapper.addMemberRights(memberRights);
    }

    /**
     *  修改
     * @param memberRights
     * @return
     */
    @Override
    public int updateMemberRights(MemberRights memberRights) {
        // 查询id是否存在
        MemberRights memberRights1 = memberRightsMapper.selectByRightId(memberRights.getRightId());
        if(memberRights1 == null){
            // 修改的对象不存在
            return 0;
        }

        return memberRightsMapper.updateMemberRights(memberRights);
    }

    /**
     *  根据id查询对象
     * @param rightId
     * @return
     */
    @Override
    public MemberRights selectByRightId(Long rightId) {
        return memberRightsMapper.selectByRightId(rightId);
    }
}
