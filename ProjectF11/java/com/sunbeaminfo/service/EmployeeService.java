package com.sunbeaminfo.service;

import java.util.List;

import com.sunbeaminfo.dto.ApiResponse;
import com.sunbeaminfo.dto.AuthRequest;
import com.sunbeaminfo.dto.AuthResp;
import com.sunbeaminfo.entities.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee addEmpDetails(Employee emp);
	Employee getEmpDetails(Long empId);
	ApiResponse deleteEmpDetails(Long empId);
	//add a method for emp signin
	AuthResp authenticateEmp(AuthRequest request);
}
