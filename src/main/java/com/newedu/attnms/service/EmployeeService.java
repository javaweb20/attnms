package com.newedu.attnms.service;

import com.github.pagehelper.PageInfo;
import com.newedu.attnms.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    int insert(Employee employee) ;
    int update(Employee employee) ;
    int delete(int empno) ;
    Employee selectOne(int empno) ;
    List<Employee> selectAll() ;

    int insert2(Employee employee) ;

    List<Employee> getEmpLikeByName(String ename) ;

    public PageInfo<Employee> findItemByPage(int currentPage, int pageSize);
}
