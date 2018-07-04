package com.newedu.attnms.controller;

import com.newedu.attnms.dao.DepartmentDao;
import com.newedu.attnms.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;


    @Autowired
    DepartmentDao departmentDao;


}
