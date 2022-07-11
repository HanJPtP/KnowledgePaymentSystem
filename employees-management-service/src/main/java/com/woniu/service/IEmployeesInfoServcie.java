package com.woniu.service;

import com.woniu.outlet.dao.mysql.po.Employeeinfo;

public interface IEmployeesInfoServcie {

    // 添加一条 employeesinfo 数据
    int saveEmployeeInfoItem(Employeeinfo employeeinfo);

    // 通过 employeeinfo ID 更新 status
    int updateEmployeeInfoStatus(Employeeinfo employeeinfo);

}
