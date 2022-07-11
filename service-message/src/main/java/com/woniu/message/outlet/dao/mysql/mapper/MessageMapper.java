package com.woniu.message.outlet.dao.mysql.mapper;

import com.woniu.message.outlet.dao.mysql.po.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yxq
* @description 针对表【tb_message】的数据库操作Mapper
* @createDate 2022-06-10 09:34:37
* @Entity com.woniu.message.outlet.dao.mysql.po.Message
*/
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}




