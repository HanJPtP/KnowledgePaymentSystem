package com.woniu.intnet.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 每分钟更新的展示有多少人看过,符合要求的有效时长有多少人
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowPeopleViewVo implements Serializable {
    /**
     * 直播id
     */
    private Long crlid;
    /**
     * 浏览人数
     */
    private Long maxNum;
    /**
     * 有效浏览人数
     */
    private Long validNum;


}
