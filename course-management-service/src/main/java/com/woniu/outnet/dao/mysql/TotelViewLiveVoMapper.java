package com.woniu.outnet.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.intnet.web.vo.TotelViewLiveVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TotelViewLiveVoMapper extends BaseMapper<TotelViewLiveVo> {

    List<TotelViewLiveVo> listTotelViewLiveVoWhileUserStatusIsY(@Param("watchtime") Long watchTime);
}
