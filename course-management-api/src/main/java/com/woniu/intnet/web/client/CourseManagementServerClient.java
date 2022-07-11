package com.woniu.intnet.web.client;

import com.woniu.intnet.web.vo.ClassCurrencyMsg;
import com.woniu.intnet.web.vo.PageInfo;
import com.woniu.intnet.web.vo.WatchUserTimeByCurrencyVo;
import com.woniu.intnet.web.vo.WatchUserTimeByLiveVo;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CourseManagementServerClient {

    @GetMapping("/show/classlive/{id}")
    public ResponseResult<List<WatchUserTimeByLiveVo>> showClassLiveWatchPage(@PathVariable("id") Long id);


    @GetMapping("/show/currencywatch/{id}")
    public ResponseResult<List<WatchUserTimeByCurrencyVo>> showWatchUserTimeByCurrencyVoByUserid(@PathVariable("id") Long id);


    /**
     * 多条件分页查询
     * @param pageNo
     * @param pageSize
     * @param name
     * @param startPrice
     * @param endPrice
     * @param startTime
     * @param endTime
     * @return
     */
    @SneakyThrows
    @GetMapping("/show/currency")
    public ResponseEntity<PageInfo<ClassCurrencyMsg>> showClassCurrencyMsgPage(@RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                                                                               @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize,
                                                                               @RequestParam(value = "name",required = false,defaultValue = "")String name,
                                                                               @RequestParam(value = "startPrice",required = false,defaultValue = "-1")Double startPrice,
                                                                               @RequestParam(value = "endPrice",required = false,defaultValue = "-1")Double endPrice,
                                                                               @RequestParam(value = "startTime",required = false,defaultValue = "")String startTime,
                                                                               @RequestParam(value = "endTime",required = false,defaultValue = "")String endTime);
}
