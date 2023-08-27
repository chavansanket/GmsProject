package com.app.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CategoryDTO;
import com.app.service.CategoryService;
//import com.app.dto.DepartmentDTO;
//import com.app.service.DepartmentService;


@RestController
@RequestMapping("/categories")
@Validated
@CrossOrigin(origins = "*")

public class CategoryController {
//	// dependency
//		@Autowired
//		private DepartmentService departmentService;
//
	@Autowired
	private CategoryService categoryService;
	
//		// add new department
//		// http://host:port/departments , method=POST
//		// req payload : dept dto
//		@PostMapping
//		public ResponseEntity<?> addNewDept(@RequestBody @Valid DepartmentDTO dto) {
//			System.out.println("in add new dept " + dto);
//			return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addNewDepartment(dto));
//		}
	
	//add new category
	// http://host:port/categories , method=POST
	//req payload : dept dto
	@PostMapping
	public ResponseEntity<?> addNewCat(@RequestBody @Valid CategoryDTO dto){
		System.out.println("in add new cat" + dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.adddNewCategory(dto));
	}
//
//		// get department details
//		// http://host:port/departments/{deptId} , method=GET
//		@GetMapping("/{deptId}")
//		public ResponseEntity<?> getDeptDetails(@PathVariable @Min(1) @Max(10) Long deptId) {
//			System.out.println("in get dept dtls " + deptId);
//			return ResponseEntity.ok(departmentService.getDepartmentDetails(deptId));
//		}
		
	// get category details
	// http://host:port/categories/{catId} , method=GET
	@GetMapping("/{catId}")
	public ResponseEntity<?> getCatDetails(@PathVariable @Min(1) @Max(10) Long catId) {
		System.out.println("in get cat dtls " + catId);
		return ResponseEntity.ok(categoryService.getCategoryDetails(catId));
	}
	
	@GetMapping
	public ResponseEntity<?> categoryList(){
		return ResponseEntity.ok(categoryService.getAllCategory());
	}
	
	
//
//		// get department n emp details
//		// http://host:port/departments/{deptId}/emps , method=GET
//		@GetMapping("/{deptId}/emps")
//		public ResponseEntity<?> getDeptAndEmpDetails(
//				@PathVariable @Min(1) @Max(10) Long deptId) {
//			System.out.println("in get dept n emp dtls " + deptId);
//			return ResponseEntity.ok(departmentService.
//					getDepartmentAndEmpDetails(deptId));
//		}

	
}
