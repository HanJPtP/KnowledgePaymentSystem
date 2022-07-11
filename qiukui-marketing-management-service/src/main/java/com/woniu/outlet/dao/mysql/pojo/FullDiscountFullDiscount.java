package com.woniu.outlet.dao.mysql.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName full_discount_full_discount
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("full_discount_full_discount")
public class FullDiscountFullDiscount implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 活动名称
     */
    @TableField("eventName")
    private String eventname;

    /**
     * 活动类型(0: 满减活动,1: 满X元包邮活动,2: 满X元满赠活动)
     */
    @TableField("typeOfActivity")
    private Integer typeofactivity;

    /**
     * 活动内容(满减信息: 例: 满100减10)
     */
    @TableField("activities")
    private String activities;

    /**
     * 适用商品范围
     */
    @TableField("scopeOfApplication")
    private Integer scopeofapplication;

    /**
     * 活动状态(0:未开始,1: 进行中,2: 已结束)
     */
    @TableField("activeStatus")
    private Integer activestatus;

    /**
     * 活动开始时间
     */
    @TableField("eventStartTime")
    private Date eventstarttime;

    /**
     * 活动结束时间
     */
    @TableField("eventEndTime")
    private Date eventendtime;


    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FullDiscountFullDiscount other = (FullDiscountFullDiscount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEventname() == null ? other.getEventname() == null : this.getEventname().equals(other.getEventname()))
            && (this.getTypeofactivity() == null ? other.getTypeofactivity() == null : this.getTypeofactivity().equals(other.getTypeofactivity()))
            && (this.getActivities() == null ? other.getActivities() == null : this.getActivities().equals(other.getActivities()))
            && (this.getScopeofapplication() == null ? other.getScopeofapplication() == null : this.getScopeofapplication().equals(other.getScopeofapplication()))
            && (this.getActivestatus() == null ? other.getActivestatus() == null : this.getActivestatus().equals(other.getActivestatus()))
            && (this.getEventstarttime() == null ? other.getEventstarttime() == null : this.getEventstarttime().equals(other.getEventstarttime()))
            && (this.getEventendtime() == null ? other.getEventendtime() == null : this.getEventendtime().equals(other.getEventendtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEventname() == null) ? 0 : getEventname().hashCode());
        result = prime * result + ((getTypeofactivity() == null) ? 0 : getTypeofactivity().hashCode());
        result = prime * result + ((getActivities() == null) ? 0 : getActivities().hashCode());
        result = prime * result + ((getScopeofapplication() == null) ? 0 : getScopeofapplication().hashCode());
        result = prime * result + ((getActivestatus() == null) ? 0 : getActivestatus().hashCode());
        result = prime * result + ((getEventstarttime() == null) ? 0 : getEventstarttime().hashCode());
        result = prime * result + ((getEventendtime() == null) ? 0 : getEventendtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eventname=").append(eventname);
        sb.append(", typeofactivity=").append(typeofactivity);
        sb.append(", activities=").append(activities);
        sb.append(", scopeofapplication=").append(scopeofapplication);
        sb.append(", activestatus=").append(activestatus);
        sb.append(", eventstarttime=").append(eventstarttime);
        sb.append(", eventendtime=").append(eventendtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}