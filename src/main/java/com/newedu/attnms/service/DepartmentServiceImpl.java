package com.newedu.attnms.service;

import com.newedu.attnms.dao.DepartmentDao;
import com.newedu.attnms.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public int insert(Department department) {
        int cnt = 0;
        try {
            cnt = departmentDao.insert(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }

    @Override
    public int update(Department department) {
        int cnt = 0;
        try {
            cnt = departmentDao.update(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }

    @Override
    public int delete(int deptno) {
        int cnt = 0;
        try {
            cnt = departmentDao.delete(deptno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }

    @Override
    public Department selectOne(int deptno) {
        Department department = null;
        try {
            department = departmentDao.selectOne(deptno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public List<Department> selectAll() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    @Override
    public List<Department> getDeptLikeOf(String dname) {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDao.getDeptLikeOf(dname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departmentList;
    }
}
