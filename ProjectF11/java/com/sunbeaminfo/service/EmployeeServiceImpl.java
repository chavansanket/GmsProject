package com.sunbeaminfo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeaminfo.custom_exceptions.ResourceNotFoundException;
import com.sunbeaminfo.dao.EmployeeDao;
import com.sunbeaminfo.dto.ApiResponse;
import com.sunbeaminfo.dto.AuthRequest;
import com.sunbeaminfo.dto.AuthResp;
import com.sunbeaminfo.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	// dep : emp dao i/f
	@Autowired
	private EmployeeDao empDao;
	//autowire : model mapper
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return empDao.findAll();
	}

	@Override
	public Employee addEmpDetails(Employee emp) {
		// API of CrudRepository : T save(T entity)
		return empDao.save(emp);
	}// auto dirty chking --> insert --> sesison closed --> rets detached emp to the
		// caller

	@Override
	public Employee getEmpDetails(Long empId) {
		// TODO Auto-generated method stub
		return empDao.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Emp id invalid !!!!!"));
	}

	@Override
	public ApiResponse deleteEmpDetails(Long empId) {
		Employee emp = getEmpDetails(empId);
		// => emp id valid
		empDao.delete(emp); // OR empDao.deleteById(empId)
		return new ApiResponse("emp details deleted !");
	}

	@Override
	public AuthResp authenticateEmp(AuthRequest request) {
		// autheticate emp
		Employee emp=empDao.
				findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email or Password !!!!!"));
		//=> valid login --> map Entity --> DTO
		//ModelMapper API : public T map(Object src , Class<T> class)
		return mapper.map(emp, AuthResp.class);
	}
	

}
