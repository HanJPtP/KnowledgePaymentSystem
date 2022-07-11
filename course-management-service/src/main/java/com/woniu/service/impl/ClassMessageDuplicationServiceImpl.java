package com.woniu.service.impl;

import com.woniu.outnet.dao.mysql.ClassMessageDuplicationMapper;
import com.woniu.outnet.dao.pojo.ClassMessageDuplication;
import com.woniu.service.IClassMessageDuplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassMessageDuplicationServiceImpl implements IClassMessageDuplicationService {

    private final ClassMessageDuplicationMapper classMessageDuplicationMapper;

    @Override
    public int insertId(ClassMessageDuplication classMessageDuplication) {
        return classMessageDuplicationMapper.insert(classMessageDuplication);
    }
}
