package com.app.service;

import java.util.List;


import com.app.dto.AddProductDto;

import com.app.dto.ProductRespDTO;
import com.app.entities.Product;



public interface ProductService {

	List<ProductRespDTO> getAllProductsFromCat(Long catId);


	Product addNewProduct(ProductRespDTO dto);
	
	//Product updateProduct(Long prodID,ProductRespDTO dto);
	
	Product deleteProduct(Long prodID);
	
	void updateProuctQuantity(Long vendorProductId,Long productId, Long quantity);
	
	

	Product getProductDetails(Long prodID);
	
	List<Product> getAllProducts();


	void updateProuctQuantity(Long vendorProductId, Long quantity);

	
}
