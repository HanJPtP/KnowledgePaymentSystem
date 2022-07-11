package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_course_msg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseMsg implements Serializable {
    /**
     * 用户id
     */
    @TableId("uid")
    private Long uid;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 学习总时长
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