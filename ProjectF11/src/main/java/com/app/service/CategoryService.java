package com.app.service;

import com.app.entities.Category;
import com.app.dto.CategoryDTO;
//import com.app.dto.DepartmentEmpsDTO;
import java.util.List;




public interface CategoryService {
	
	
	//	List<Department> getAllDepartmens();
List<Category> getAllCategory();
	
	
	//	DepartmentDTO getDepartmentDetails(Long deptId);
CategoryDTO getCategoryDetails(Long catId);

	//	DepartmentDTO addNewDepartment(DepartmentDTO dept);
CategoryDTO adddNewCategory(CategoryDTO cat);

	//	DepartmentEmpsDTO getDepartmentAndEmpDetails(Long deptId);
}

