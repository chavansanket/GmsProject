package com.app.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ProductRespDTO;
import com.app.dto.ProductRespDTO2;
import com.app.entities.Product;
import com.app.dto.AddProductDto;

import com.app.service.ProductService;
//import com.app.service.ImageHandlingService;
import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	
	
	@GetMapping("/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable Long productId){
		System.out.println("in get prods "+ productId);
		return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductDetails(productId));
	}
	
	@GetMapping("/categories{catId}")
	public ResponseEntity<?> getProdsByCategory(@PathVariable Long catId){
		System.out.println("in get prods "+ catId);
		List<ProductRespDTO> list = productService.getAllProductsFromCat(catId);
		if (list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<?>  addProdToExistingCat(@RequestBody @Valid ProductRespDTO dto){
		System.out.println("in add prod "+ dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addNewProduct(dto));
	}
	
	
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
		System.out.println("produtId del---"+productId);
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.deleteProduct(productId));
	}
	
//	@PutMapping("/{prodId}")
//	public ResponseEntity<?> updateProd(@RequestBody @Valid ProductRespDTO dto, @PathVariable Long prodId)
//	{
//		System.out.println("in update prod "+ dto);
//		return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateProduct(prodId ,dto));
//	}
	
	@PutMapping("/{vendorProductId}/{productId}")
	public void updateProductQuantity(@PathVariable Long vendorProductId,@PathVariable Long productId,@RequestBody @Valid Long quantity)
	{
		System.out.println("in update prodquantity ");
		
		productService.updateProuctQuantity(vendorProductId,productId, quantity);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllProduct(){
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@PutMapping("/{vendorProductId}")
		public void updateProductQuantity(@PathVariable Long vendorProductId,@RequestBody @Valid ProductRespDTO2 dto)
		{
			System.out.println("in update prodquantity ");
			
			System.out.println("********** "+vendorProductId);
			System.out.println("********** "+dto.getQty());
			
			productService.updateProuctQuantity(vendorProductId, dto.getQty());
		}

	
	
		
}
