package com.newedu.attnms.controller;

import com.github.pagehelper.PageInfo;
import com.newedu.attnms.dao.DepartmentDao;
import com.newedu.attnms.dao.EmployeeDao;
import com.newedu.attnms.entity.Department;
import com.newedu.attnms.entity.Employee;
import com.newedu.attnms.service.DepartmentService;
import com.newedu.attnms.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @Autowired
    DepartmentService departmentService;

    /**
     * 查询所有员工返回列表页面，有分页效果
     * 传三个参数，currentPage 当前页码，pageSize每页显示多少数据，HttpServletRequest用来封装数据
     * 使用@RequestParam进行参数设置，value和defaultValue意指当URL直接请求“/admin/myMessage”时，默认传值参数名称是currentPage,默认值是1；默认传值参数名称是pageSize，默认值是5。这次请求是在别的页面跳转此页面时发生。当进行分页请求时，就会真实传入这2个参数。
     * @param currentPage
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping(value="/emps")
    public String list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,Map map){

//        List<Employee> empList = employeeService.selectAll();
        PageInfo<Employee> empList = employeeService.findItemByPage(currentPage,pageSize);
        //将查询的结果放置到请求域中
        map.put("empList",empList);
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping(value="/emp")
    public String addEmp(Map map){

        //来到员工添加页面,查出所有的部门，在页面显示
        List<Department> departmentList = departmentService.selectAll();


        //将查询到的部门信息保存到request域对象中
        map.put("departmentList",departmentList);


        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "emp/save";
    }


}
