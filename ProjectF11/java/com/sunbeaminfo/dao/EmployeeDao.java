package com.sunbeaminfo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeaminfo.entities.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
//how to get all emps ?  use inherited API : findAll
	//save emp : save : inherited  method
	//delete emp details : findById , delete(T entity)
	//for emp signin : finder method
	Optional<Employee> findByEmailAndPassword(String em,String pass);
}
