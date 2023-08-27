package com.app.respository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.entities.SubCategory;
import java.lang.String;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long>{
	
	List<SubCategory> findAll();
	
//	List<SubCategory> findBySubCategoryName(String subCategoryName);
	
	List<SubCategory> findBySubCatName(String subCatname);
	
	List<SubCategory> findByCategory(Category category);
	
	@Query("select sC from SubCategory sC where sC.category.categoryId=:catId")
	List<SubCategory> findByCategory (Long catId);
}