package com.sunbeaminfo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeaminfo.dto.ApiResponse;
import com.sunbeaminfo.dto.AuthRequest;
import com.sunbeaminfo.entities.Employee;
import com.sunbeaminfo.service.EmployeeService;

@RestController // mandatory class level anno , consists of =@Controller : cls level
				// +@ResponseBody : ret type of req handling
				// methods(@RequestMapping/@GetMapping...)
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	// dep : service layer i.f
	@Autowired
	private EmployeeService empService;

	public EmployeeController() {
		System.out.println("in ctor of " + getClass());
	}

	// add a method : REST API end point , to get all emps
	// Req : http://host:port/employees , method ; GET
	@GetMapping
	public List<Employee> listAllEmps() {
		return empService.getAllEmployees();
	}

	// add a method : REST API end point , to add new emp details
	// Req : http://host:port/employees , method ; POST
	@PostMapping
	public Employee saveEmpDetails(@RequestBody Employee emp)
	// @RequestBody : mandatory method arg level annotation for de-ser / un
	// marshalling
	// => json ---> java
	{
		System.out.println("in save emp " + emp.getId());// id : null (transient)
		return empService.addEmpDetails(emp);
	}

	// add a method : REST API end point , to delete emp details
	// Req : http://host:port/employees/empId , method : DELETE
	@DeleteMapping("/{empId}")
	// @PathVariable : method arg level anno , for binding URI template var to req
	// handling method arg.
	public ApiResponse deleteEmpDetails(@PathVariable Long empId) {
		System.out.println("in del emp " + empId);
		return empService.deleteEmpDetails(empId);
	}

	// add a method : REST API end point , to get emp details by id
	// Req : http://host:port/employees/empId , method : GET
	@GetMapping("/{id}")
	public Employee getEmpDetailsById(@PathVariable Long id) {
		System.out.println("in get emp dtls " + id);
		return empService.getEmpDetails(id);
	}
	// add a method : REST API end point , to get emp details by id
	// Req : http://host:port/employees/empId , method : GET

	@PutMapping
	public Employee updateEmpDetails(@RequestBody Employee detachedEmp) {
		System.out.println("in update emp " + detachedEmp.getId());// not null
		// validate
		empService.getEmpDetails(detachedEmp.getId());
		// => emp exists by the id --> continue to update
		return empService.addEmpDetails(detachedEmp);
	}

	// add a method : REST API end point , to emp signin
	// Req : http://host:port/employees/signin method=POST
	// req payload : req dto
	// resp : resp dto
	@PostMapping("/signIn")
	public ResponseEntity<?> authenticateEmp(@RequestBody @Valid AuthRequest request) {
		System.out.println("in sign in " + request);
	//	try {
			return new ResponseEntity<>(empService.authenticateEmp(request), 
					HttpStatus.OK);
//		} catch (RuntimeException e) {
//			System.out.println(e);
//			return ResponseEntity.status
//					(HttpStatus.NOT_FOUND).
//					body(new ApiResponse(e.getMessage()));
//		}
	}

}
