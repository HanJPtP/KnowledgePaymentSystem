package com.woniu.users.service;

import com.woniu.users.outlet.dao.mysql.po.UserLabelInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IUserLabelInfoService {
    /**
     *  新增用户标签信息表数据
     * @param uid
     * @param labelIds
     * @return
     */
    void insertUserLabel(Long uid,String labelIds);

    /**
     *  根据uid查询标签名集合
     * @param uid
     * @return
     */
    List<String> listLabelName(Long uid);
}
