package com.app.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.entities.VendorProducts;

public interface ProductRepo extends JpaRepository<Product, Long>{
	
	List<Product> findAll();
	
	
//	List<Product> findByProductName(String productName);
//	
//	List<Product> findByCategory(Category category);
	
	//select e from Employee e where e.dept.id=:id
	@Query("select p from Product p where p.vendorProduct.category.categoryId=:catId")
	List<Product> findByCategory(Long catId);


	Product findByVendorProduct(VendorProducts vendorProduct); 
	

	
	
}
