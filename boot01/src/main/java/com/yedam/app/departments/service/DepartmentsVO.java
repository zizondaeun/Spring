package com.yedam.app.departments.service;

import lombok.Data;

@Data
public class DepartmentsVO {
	private Integer departmentId;
	private String departmentName;
	private int managerId;
	private int locationId;
}
