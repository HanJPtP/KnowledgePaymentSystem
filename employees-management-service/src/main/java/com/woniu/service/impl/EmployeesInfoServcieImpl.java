package com.woniu.service.impl;

import com.woniu.outlet.dao.mysql.mapper.EmployeeinfoMapper;
import com.woniu.outlet.dao.mysql.po.Employeeinfo;
import com.woniu.service.IEmployeesInfoServcie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeesInfoServcieImpl implements IEmployeesInfoServcie {

    private final EmployeeinfoMapper employeeinfoMapper;

    @Override
    public int saveEmployeeInfoItem(Employeeinfo employeeinfo) {
        return employeeinfoMapper.insert(employeeinfo);
    }

    @Override
    public int updateEmployeeInfoStatus(Employeeinfo employeeinfo) {
        return employeeinfoMapper.updateEmployeeInfoItemStatus(employeeinfo);
    }
}
