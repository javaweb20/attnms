package com.newedu.attnms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Employee {

    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private String hiredate;
    private double sal;
    private double bonus;
    private Department department;



    public Employee(int empno,String ename,String job,int mgr,String hiredate,double sal,double bonus ){

        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.bonus = bonus;
    }

}
