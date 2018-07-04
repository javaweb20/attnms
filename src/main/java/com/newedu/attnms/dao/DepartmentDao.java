package com.newedu.attnms.dao;

import com.newedu.attnms.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentDao {

    int insert(Department department) throws Exception;
    int update(Department department) throws Exception;
    int delete(int deptno) throws Exception;
    Department selectOne(int deptno) throws Exception;
    List<Department> selectAll() throws Exception;

    List<Department> getDeptLikeOf(String dname) throws Exception;

}
