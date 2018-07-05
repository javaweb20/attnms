package com.newedu.attnms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newedu.attnms.dao.EmployeeDao;
import com.newedu.attnms.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public int insert(Employee employee) {
        int cnt = 0;
        try {
            cnt = employeeDao.insert(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt ;
    }

    @Override
    public int update(Employee employee) {
        int cnt = 0;
        try {
            cnt = employeeDao.update(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt ;
    }

    @Override
    public int delete(int empno) {
        int cnt = 0;
        try {
            cnt = employeeDao.delete(empno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt ;
    }

    @Override
    public Employee selectOne(int empno) {
        Employee employee = null;
        try {
            employee = employeeDao.selectOne(empno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee ;
    }

    @Override
    public List<Employee> selectAll() {
        List<Employee> employeeList = null;
        try {
            employeeList = employeeDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList ;
    }

    @Override
    public int insert2(Employee employee) {
        int cnt = 0;
        try {
            cnt = employeeDao.insert2(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt ;
    }

    @Override
    public List<Employee> getEmpLikeByName(String ename) {
        List<Employee> employeeList = null;
        try {
            employeeList = employeeDao.getEmpLikeByName(ename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList ;
    }

    @Override
    public PageInfo<Employee> findItemByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Employee> allEmployees = null;
        try {
            allEmployees = employeeDao.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PageInfo<Employee> pageInfo = new PageInfo<>(allEmployees);
        return pageInfo;
    }


}
