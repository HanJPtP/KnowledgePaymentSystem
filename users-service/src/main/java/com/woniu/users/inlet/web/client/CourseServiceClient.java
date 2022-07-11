package com.woniu.users.inlet.web.client;

import com.woniu.intnet.web.vo.WatchUserTimeByCurrencyVo;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *  调用课程服务
 */
@FeignClient("course-management-service")
public interface CourseServiceClient {


    @GetMapping("/show/currencywatch/{id}")
    ResponseResult<List<WatchUserTimeByCurrencyVo>> showWatchUserTimeByCurrencyVoByUserid(@PathVariable("id") Long id);
}
