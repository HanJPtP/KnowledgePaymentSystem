package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_learn_msg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLearnMsg implements Serializable {
    /**
     * 用户id
     */
    @TableId("uid")
    private Long uid;

    /**
     * 学习总课程数
     */
    private Integer totalCourse;

    /**
     * 已完成总课程数
     */
    private Integer finishedCourse;

    /**
     * 学习总时长（小时）
     */
    private String totalTime;

    /**
     * 今日学习时长(小时）（每日时间会重置）
     */
    private String todayTime;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}