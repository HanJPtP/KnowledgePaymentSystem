package com.woniu.outnet.dao.pojo;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.woniu.intnet.web.vo.WatchUserTimeByLiveVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuqi
 * @since 2022-06-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClassLiveWatch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    private Long crlid;

    private Long userid;

    private Date firsttime;

    private Date quittime;

    private Long watchtime;
    @TableField(exist = false)
    private ClassRecordLive classRecordLive;


    public WatchUserTimeByLiveVo MakeClassLiveWatchToWatchUserTimeByLiveVo(WatchUserTimeByLiveVo watchUserTimeByLiveVo,ClassLiveWatch classLiveWatch){
        watchUserTimeByLiveVo.setId(classLiveWatch.getClassRecordLive().getId());
        watchUserTimeByLiveVo.setLiveStatus(classLiveWatch.getClassRecordLive().getLiveStatus());
        watchUserTimeByLiveVo.setEndtime(classLiveWatch.getClassRecordLive().getEndtime());
        watchUserTimeByLiveVo.setEstarttime(classLiveWatch.getClassRecordLive().getEstarttime());
        watchUserTimeByLiveVo.setStarttime(classLiveWatch.getClassRecordLive().getStarttime());
        watchUserTimeByLiveVo.setUserid(classLiveWatch.getUserid());
        watchUserTimeByLiveVo.setWatchtime(classLiveWatch.getWatchtime());
        watchUserTimeByLiveVo.setImg(classLiveWatch.getClassRecordLive().getImg());
        watchUserTimeByLiveVo.setName(classLiveWatch.getClassRecordLive().getName());
        watchUserTimeByLiveVo.setNum(classLiveWatch.getClassRecordLive().getNum());
        watchUserTimeByLiveVo.setUseridPlay(classLiveWatch.getClassRecordLive().getUserid());
        watchUserTimeByLiveVo.setLiveStatus(classLiveWatch.getClassRecordLive().getLiveStatus());
        watchUserTimeByLiveVo.setStatus(classLiveWatch.getClassRecordLive().getStatus());
        watchUserTimeByLiveVo.setOrdernum(classLiveWatch.getClassRecordLive().getOrdernum());
        return watchUserTimeByLiveVo;
    }
}
