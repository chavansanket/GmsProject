package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ApiException;
import com.app.custom_exceptions.ResourceNotFoundException;
//import com.app.custom_exceptions.ApiException;
//import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.AddProductDto;

import com.app.dto.ProductRespDTO;
//import com.app.entities.Address;
import com.app.entities.Category;

import com.app.entities.Product;
import com.app.entities.VendorProducts;
//import com.app.entities.Project;
//import com.app.repository.AddressRepository;
import com.app.respository.CategoryRepo;
import com.app.respository.ProductRepo;
import com.app.respository.VendorProductRepo;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private ProductRepo prodRepo;
	
	@Autowired
	private VendorProductRepo vendorProdRepo;
	
	
	
	
	
	
	
	@Autowired
	private ModelMapper mapper;
	

	@Override
	public List<ProductRespDTO> getAllProductsFromCat(Long catId) {

		System.out.println("in service  prod get");
//		List<Product> prodList=prodRepo.findByCategory(catId);
//		return prodList.stream() //Stream<Emp>
//				.map(prod ->mapper.map(prod, ProductRespDTO.class))//Stream<DTO>
//				.collect(Collectors.toList());
		
		
		return null;
	}

	@Override
	public Product addNewProduct(ProductRespDTO dto) {

//			Category cat = catRepo.findById(dto.getCatId())
//					.orElseThrow(()->new ResourceNotFoundException("Invalid Cat id !!!"));
			Product product =mapper.map(dto, Product.class);
			product.setVendorProduct(vendorProdRepo.findById(dto.getVendorProductId()).orElseThrow(() -> new ResourceNotFoundException("Invalid Prod ID , Prod not found !!!!")));
			
			System.out.println(product);
			
			
			//cat.addProduct(product);//cascade on save

		return mapper.map(prodRepo.save(product),Product.class);
		
	}

//	@Override
//	public Product updateProduct(Long prodID, ProductRespDTO dto) {
//		
//		Product prod = prodRepo.findById(prodID)
//				.orElseThrow(() -> new ResourceNotFoundException("Invalid Prod ID , Prod not found !!!!"));
//		Category cat = catRepo.findById(dto.getCatId())
//				.orElseThrow(()-> new ResourceNotFoundException("Invalid Cat Id"));
//		mapper.map(dto,prod);
//		prod.setProductId(prodID);
//		//cat.addProduct(prod);
//		return mapper.map(prod,Product.class);
//		
//	}

	
	@Override
	public Product getProductDetails(Long prodID) {
		System.out.println("in service get products "+ prodID);
		Product product = prodRepo.findById(prodID)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Prod ID!!!"));
		return product;
	}

	@Override
	public Product deleteProduct(Long prodID) {
		
		//System.out.println("Optional"+product1);
		Product product = prodRepo.findById(prodID)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Prod ID!!!"));
		//System.out.println("product"+product);
		prodRepo.delete(product);
		return product;
	}

	@Override
	public void updateProuctQuantity(Long vendorProductId,Long productId, Long quantity) {
		// TODO Auto-generated method stub
		System.out.println("in service update prod qty ");
		System.out.println(vendorProductId);
		System.out.println(productId);
		Product product = prodRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Invalid VendorProductId"));
//		prodRepo.findBy
		
		VendorProducts vendorProduct = vendorProdRepo.findById(vendorProductId).orElseThrow(()-> new ResourceNotFoundException("Invalid VendorProductId"));
		
		vendorProduct.setProductQuantity((int)(vendorProduct.getProductQuantity()-quantity));
		
		product.setPq(product.getPq()+quantity);
		
		vendorProdRepo.save(vendorProduct);
		
		prodRepo.save(product);
		
	
	}
	
	@Override
	public void updateProuctQuantity(Long vendorProductId, Long quantity) {
		// TODO Auto-generated method stub
		
		
		//Product product = prodRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Invalid VendorProductId"));
//		prodRepo.findBy
		
		VendorProducts vendorProduct = vendorProdRepo.findById(vendorProductId).orElseThrow(()-> new ResourceNotFoundException("Invalid VendorProductId"));
		
		Product product = prodRepo.findByVendorProduct(vendorProduct);
		//Product product = prodRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Invalid VendorProductId"));

		
		
		vendorProduct.setProductQuantity((int)(vendorProduct.getProductQuantity()-quantity));
		
		product.setPq(product.getPq()+quantity);
		
		vendorProdRepo.save(vendorProduct);
		
		prodRepo.save(product);
		
	
	}

	@Override
	public List<Product> getAllProducts() {
		
		return prodRepo.findAll();
	}
	

}
