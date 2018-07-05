package com.newedu.attnms.service;

import com.newedu.attnms.entity.Department;

import java.util.List;

public interface DepartmentService {

    int insert(Department department) ;
    int update(Department department) ;
    int delete(int deptno) ;
    Department selectOne(int deptno) ;
    List<Department> selectAll() ;

    List<Department> getDeptLikeOf(String dname) ;

}
