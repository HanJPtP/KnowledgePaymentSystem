package com.woniu.inlet.web.controller;


import com.woniu.inlet.web.fo.QkMarketingDetailsFO;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.outlet.dao.mysql.pojo.QkMarketingDetails;
import com.woniu.service.impl.QkMarketingDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qk
 * @since 2022-06-13
 */
@RestController
@RequiredArgsConstructor
public class QkMarketingDetailsController {

    private final QkMarketingDetailsServiceImpl marketingDetailsService;
    /**
     * 新增营销活动
     * @param marketingDetailsFo
     * @return
     */
//    @PreAuthorize("hasAuthority('cashier:live:list')")
    @PostMapping ("/marketingDetails/add")
    public ResponseResult<Void> addMarketingDetails(@RequestBody QkMarketingDetailsFO marketingDetailsFo){
        marketingDetailsFo.setAddTime(LocalDateTime.now()); //设置当前时间
        ResponseResult<Void> result = null;
        QkMarketingDetails marketingDetails = QkMarketingDetails.builder()
                .activityName(marketingDetailsFo.getActivityName())
                .marketingTypeId(marketingDetailsFo.getMarketingTypeId())
                .activityTime(marketingDetailsFo.getActivityTime())
                .memberShip(marketingDetailsFo.getMemberShip())
                .activeStatusId(marketingDetailsFo.getActiveStatusId())
                .addTime(marketingDetailsFo.getAddTime())
                .activityChannel(marketingDetailsFo.getActivityChannel())
                .ruleOfActivity(marketingDetailsFo.getRuleOfActivity())
                .notificationChannel(marketingDetailsFo.getNotificationChannel())
                .notificationTime(marketingDetailsFo.getNotificationTime())
                .notificationTemplate(marketingDetailsFo.getNotificationTemplate())
                .build();
        int i = marketingDetailsService.addMarketingDetails(marketingDetails);
        if (i > 0){
            result = new ResponseResult<Void>(200,"添加成功");
        }else {
            result = new ResponseResult<Void>(500,"添加失败");
        }
        return result;
    }


}

