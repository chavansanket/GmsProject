package com.app.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ApiException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.HomeProdRespoDTO;
import com.app.dto.VendorProductDto;
import com.app.entities.Category;
import com.app.entities.SubCategory;
import com.app.entities.Vendor;
import com.app.entities.VendorProducts;
import com.app.respository.CategoryRepo;
import com.app.respository.SubCategoryRepo;
import com.app.respository.VendorProductRepo;
import com.app.respository.VendorRepo;

@Service
@Transactional
public class VendorProductServiceImpl implements VendorProductService{

	@Autowired
	private VendorProductRepo venProdRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private SubCategoryRepo subCatRepo;
	
	@Autowired
	private VendorRepo vendorRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Value("${folder.location}")
	private String folderLocation;

	
	@Override
	public List<VendorProducts> getVendorProdList() {
		return venProdRepo.findAll();
	}
	
	@Override
	public VendorProducts getVendorProdById(Long vendProdId) {

		VendorProducts vProd =venProdRepo.findByVendorProductId(vendProdId) ;
//				.orElseThrow(()-> new ResourceNotFoundException("Invalid prod id"));
		return vProd;
				
	}
	@Override
	public VendorProducts addVendorProduct(VendorProductDto dto) {
		
		Category category = categoryRepo.findById(dto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Invalid category id"));
		
		SubCategory subCategory = subCatRepo.findById(dto.getSubCatId()).orElseThrow(()-> new ResourceNotFoundException("Invalid SubCat Id"));
		
		Vendor vendor = vendorRepo.findById(dto.getVendorId())
				.orElseThrow(()->new ResourceNotFoundException("Invalid Vendor id !!!"));
		
		VendorProducts vendorProd = mapper.map(dto,VendorProducts.class);
		
//		vendorProd.setVendor(vendor);
//		vendorProd.setCategory(category);
//		vendorProd.setSubcategory(subCategory);
		
		category.addProduct(vendorProd);
		subCategory.addProducts(vendorProd);
		vendor.addProducts(vendorProd);
		
		System.out.println("vendorProd in serImple" + vendorProd);
		return venProdRepo.save(vendorProd);
	}

	@Override
	public VendorProducts updateVendorProduct(Long venProdId, VendorProductDto dto) {
		
Category category = categoryRepo.findById(dto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Invalid category id"));
		
		SubCategory subCategory = subCatRepo.findById(dto.getSubCatId()).orElseThrow(()-> new ResourceNotFoundException("Invalid SubCat Id"));
		
		Vendor vendor = vendorRepo.findById(dto.getVendorId())
				.orElseThrow(()->new ResourceNotFoundException("Invalid Vendor id !!!"));
		
		VendorProducts venProd = venProdRepo.findById(venProdId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid VenProd id !!!"));
		
		
		mapper.map(dto, venProd);
		
		venProd.setVendorProductId(venProdId);
//		System.out.println("dto ==="+dto);
//		System.out.println("vendor id is ==="+ venProd.getVendor().getVendorId());
		
		venProd.setVendor(vendor);
		venProd.setCategory(category);
		venProd.setSubcategory(subCategory);
		venProdRepo.save(venProd);
		
		return venProd;
	}

	@Override
	public List<HomeProdRespoDTO> getProdProd() {
		// TODO Auto-generated method stub
		return venProdRepo.listOfProd();
		}

	@Override
	public List<VendorProductDto> getVenProdByVendorId(Long vendorId) {
		Vendor vendor = vendorRepo.findById(vendorId)
				.orElseThrow(()->new ResourceNotFoundException("invalid vendorId"));
		List<VendorProductDto> vpDtoReturnList = new ArrayList<VendorProductDto>();
		List<VendorProducts> vpEntiList =venProdRepo.findByVendor(vendor);
		
		for(int i=0;i<vpEntiList.size();i++) {
			VendorProductDto vendProdDto = mapper.map(vpEntiList.get(i), VendorProductDto.class);
			vendProdDto.setCategoryId(vpEntiList.get(i).getCategory().getCategoryId());
			vendProdDto.setSubCatId(vpEntiList.get(i).getSubcategory().getSubCatId());
			vendProdDto.setVendorId(vendorId);
			vpDtoReturnList.add(vendProdDto);
		}
		
//		return venProdRepo.findByVendor(vendor);
		return vpDtoReturnList;
	}
	
	@PostConstruct
	public void init() {
	    System.out.println("in init " + folderLocation);
	    // chk if folder exists --yes --continue
	    File folder = new File(folderLocation);
	    if (folder.exists()) {
	        System.out.println("folder exists alrdy !");
	    } else {
	        // no --create a folder
	        folder.mkdir();
	        System.out.println("created a folder !");
	    }
	}
	
	@Override
	public ApiResponse uploadImage(Long productId, MultipartFile image) throws IOException {
	    // get emp from emp id
	    VendorProducts venProduct= venProdRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Invalid product ID!!!!"));
	    // emp found --> PERSISTENT
	    // store the image on server side folder
	    String path = folderLocation.concat(image.getOriginalFilename());
	    System.out.println(path);
	    // Use FileUtils method : writeByte[] --> File
	    FileUtils.writeByteArrayToFile(new File(path), image.getBytes());
	    // set image path

	    venProduct.setImg(path);
	    // OR to store the img directly in DB as a BLOB
	    // emp.setImage(image.getBytes());
	    return new ApiResponse("Image file uploaded successfully for emp id " + productId);
	}
	
	@Override
	public byte[] downloadImage(Long productId) throws IOException {
	    // get emp by id
	    VendorProducts venProduct = venProdRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Invalid emp ID!!!!"));
	    // emp found --> PERSISTENT
	    String path = venProduct.getImg();
	    if (path != null) {
	        // path ---> File --> byte[]
	        return FileUtils.readFileToByteArray(new File(path));
	        // OR from DB : return emp.getImage();
	    } else
	        throw new ApiException("Image not yet assigned !!!!");
	}


	
	
}
