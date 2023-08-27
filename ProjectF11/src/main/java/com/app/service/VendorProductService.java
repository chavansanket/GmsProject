package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.HomeProdRespoDTO;
import com.app.dto.VendorProductDto;
import com.app.entities.VendorProducts;

public interface VendorProductService {
	
	List<VendorProducts> getVendorProdList();
	
	VendorProducts getVendorProdById(Long vendProdId);
	
	VendorProducts addVendorProduct(VendorProductDto dto);
	
	VendorProducts updateVendorProduct(Long venProdId, VendorProductDto dto);

	List<HomeProdRespoDTO> getProdProd(); 
	
	List<VendorProductDto> getVenProdByVendorId(Long vendorId);

	ApiResponse uploadImage(Long productId, MultipartFile image) throws IOException;

	byte[] downloadImage(Long productId) throws IOException;
	
	
}
