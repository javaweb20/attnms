package com.newedu.attnms.dao;

import com.newedu.attnms.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface EmployeeDao {

    int insert(Employee employee) throws SQLException;
    int update(Employee employee) throws SQLException;
    int delete(int empno) throws SQLException;
    Employee selectOne(int empno) throws SQLException;
    List<Employee> selectAll() throws SQLException;

    int insert2(Employee employee) throws SQLException;

    List<Employee> getEmpLikeByName(String ename) throws SQLException;
}
