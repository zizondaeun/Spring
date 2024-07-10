package com.yedam.app.emp.service;

import java.util.Date;

import lombok.Data;

@Data
public class EmpVO {
	private Integer empid;
	private String empname;
	private int mgr;
	private double sal;
	private int deptid;
	private Date hiredate;
}
