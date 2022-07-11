package com.woniu.service.impl;

import com.woniu.intnet.web.vo.EmailConfigInfoVo;
import com.woniu.outnet.dao.mysql.ClassEmailConfigureInfoMapper;
import com.woniu.outnet.dao.pojo.ClassEmailConfigureInfo;
import com.woniu.service.IClassEmailConfigureInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassEmailConfigureInfoServiceImpl implements IClassEmailConfigureInfoService {

    private final ClassEmailConfigureInfoMapper classEmailConfigureInfoMapper;

    /**
     * 该数据库中只有这一条数据
     * @return
     * @throws Exception
     */
    @Override
    public ClassEmailConfigureInfo getClassEmailConfigureInfoOnlyOne() throws Exception {
        return classEmailConfigureInfoMapper.selectOne(null);
    }

    /**
     * 新增,修改都只能修改这一条数据
     * @param emailConfigInfoVo
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassEmailConfigureInfoById(EmailConfigInfoVo emailConfigInfoVo) throws Exception {
        ClassEmailConfigureInfo classEmailConfigureInfo = new ClassEmailConfigureInfo();
        classEmailConfigureInfo.setEmailConfigInfoVoToThis(emailConfigInfoVo);
        ClassEmailConfigureInfo classEmailConfigureInfoOnlyOne = this.getClassEmailConfigureInfoOnlyOne();
        classEmailConfigureInfo.setId(classEmailConfigureInfoOnlyOne.getId());
        return classEmailConfigureInfoMapper.updateById(classEmailConfigureInfo);
    }
}
