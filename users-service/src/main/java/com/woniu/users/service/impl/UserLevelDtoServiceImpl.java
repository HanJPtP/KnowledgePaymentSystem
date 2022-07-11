package com.woniu.users.service.impl;

import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.users.inlet.web.dto.UserLevelDto;
import com.woniu.users.outlet.dao.mysql.mapper.LevelRoleMapper;
import com.woniu.users.outlet.dao.mysql.mapper.LevelupGiftMapper;
import com.woniu.users.outlet.dao.mysql.mapper.UserLevelMapper;
import com.woniu.users.outlet.dao.mysql.po.LevelRole;
import com.woniu.users.outlet.dao.mysql.po.LevelupGift;
import com.woniu.users.outlet.dao.mysql.po.UserLevel;
import com.woniu.users.service.IUserLevelDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserLevelDtoServiceImpl implements IUserLevelDtoService {

    private final UserLevelMapper userLevelMapper;

    private final LevelRoleMapper levelRoleMapper;

    private final LevelupGiftMapper levelupGiftMapper;


    /**
     *  新增会员等级信息
     * @param userLevelDto
     * @return
     */
    @Override
    public int addUserLevelDto(UserLevelDto userLevelDto) {
        // 判断新增的等级数和等级名是否合理，等级数和等级名不能重复
        List<UserLevel> userLevels = userLevelMapper.selectUserLevel(userLevelDto.getLevelNumber(), userLevelDto.getLevelName());
        if(userLevels.size() > 0){
            // 有重复等级数或等级名，新增失败
            return 0;
        }
        try {
            SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator(1,5);
            // 得到userLevel对象
            UserLevel userLevel = new UserLevel();
            userLevel.setLevelId(snowFlakeGenerator.nextId());
            userLevel.setLevelNumber(userLevelDto.getLevelNumber());
            userLevel.setLevelName(userLevelDto.getLevelName());
            userLevel.setBgcolor(userLevelDto.getBgcolor());
            // 得到升级规则id
            Long roleid = snowFlakeGenerator.nextId();
            userLevel.setRoleid(roleid);
            // 得到礼物规则id
            Long giftid = snowFlakeGenerator.nextId();
            userLevel.setGiftid(giftid);
            userLevel.setMinPoint(userLevelDto.getMinPoint());
            userLevel.setMaxPoint(userLevelDto.getMaxPoint());
            userLevel.setRightid(userLevelDto.getRightIds());
            userLevel.setModifiedTime(new Date());

            // 得到levelRole对象
            LevelRole levelRole = new LevelRole();
            levelRole.setRoleid(roleid);
            levelRole.setIsmember(userLevelDto.getIsmember());
            levelRole.setMoney(userLevelDto.getMoney());
            levelRole.setGrowthNum(userLevelDto.getGrowthNum());

            // 得到levelUpGift对象
            LevelupGift levelupGift = new LevelupGift(giftid, userLevelDto.getCouponId(), userLevelDto.getPoints());

            // 调用新增方法
            userLevelMapper.addUserLevel(userLevel);
            levelRoleMapper.addLevelRole(levelRole);
            levelupGiftMapper.addLevelUpGift(levelupGift);
            return 1;
        } catch(Exception e){
            return 0;
        }
    }

    /**
     *  根据levelId查询会员等级信息
     * @param levelId
     * @return
     */
   public UserLevelDto getUserLevelById(Long levelId){
       UserLevel userLevel = userLevelMapper.getByLevelId(levelId);
       // 根据roleid查询对象
       LevelRole levelrole = levelRoleMapper.getLevelRoleById(userLevel.getRoleid());
       // 根据giftId查询对象
       LevelupGift levelupGift = levelupGiftMapper.getByGiftId(userLevel.getGiftid());

       // 得到UserLevelDto对象信息
       UserLevelDto userLevelDto = new UserLevelDto(userLevel.getLevelId(), userLevel.getLevelNumber(),
               userLevel.getLevelName(), userLevel.getBgcolor(), userLevel.getMinPoint(),userLevel.getMaxPoint(),
               userLevel.getRightid(),levelrole.getIsmember(),levelrole.getMoney(),levelrole.getGrowthNum(),
               levelupGift.getCouponid(),levelupGift.getPoints());

       return userLevelDto;
   }

    /**
     *  修改会员等级信息
     * @param userLevelDto
     * @return
     */
    @Override
    public int updateUserLevelDto(UserLevelDto userLevelDto) {
        UserLevel userLevel1 = userLevelMapper.selectById(userLevelDto.getLevelId());
        if(userLevel1 == null){
            return 0;
        }
        try {
            // 得到userlevel对象，得到roleid，giftId
            UserLevel userLevel = userLevelMapper.getByLevelId(userLevelDto.getLevelId());

            // 得到userLevel对象
            if (userLevelDto.getBgcolor() != null) {
                userLevel.setBgcolor(userLevelDto.getBgcolor());
            }
            if(userLevelDto.getMinPoint() != null) {
                userLevel.setMinPoint(userLevelDto.getMinPoint());
            }
            if(userLevelDto.getMaxPoint() != null) {
                userLevel.setMaxPoint(userLevelDto.getMaxPoint());
            }
            if(userLevelDto.getRightIds() != null) {
                userLevel.setRightid(userLevelDto.getRightIds());
            }
            userLevel.setModifiedTime(new Date());

            // 得到levelRole对象
            LevelRole levelRole = new LevelRole();
            levelRole.setRoleid(userLevel.getRoleid());
            if(userLevelDto.getIsmember() != null) {
                levelRole.setIsmember(userLevelDto.getIsmember());
            }
            if(userLevelDto.getMoney() != null) {
                levelRole.setMoney(userLevelDto.getMoney());
            }
            if(userLevelDto.getGrowthNum() != null) {
                levelRole.setGrowthNum(userLevelDto.getGrowthNum());
            }

            // 得到levelUpGift对象userLevel.getGiftid(), userLevelDto.getCouponId(), userLevelDto.getPoints()
            LevelupGift levelupGift = new LevelupGift();
            if(userLevel.getGiftid() != null){
                levelupGift.setGiftId(userLevel.getGiftid());
            }
            if(userLevelDto.getCouponId() != null){
                levelupGift.setCouponid(userLevelDto.getCouponId());
            }
            if(userLevelDto.getPoints() != null){
                levelupGift.setPoints(userLevelDto.getPoints());
            }

            // 调用修改方法
            userLevelMapper.updateUserLevel(userLevel);
            if(levelRole.getGrowthNum() != null || levelRole.getIsmember() != null || levelRole.getMoney() != null) {
                levelRoleMapper.updateLevelRole(levelRole);
            }
            if(levelupGift.getPoints() != null || levelupGift.getCouponid() != null) {
                levelupGiftMapper.updateLevelUpGift(levelupGift);
            }
            return 1;
        } catch(Exception e){
            return 0;
        }
    }
}
