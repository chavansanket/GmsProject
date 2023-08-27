package com.app.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.custom_exceptions.ResourceNotFoundException;

import com.app.dto.SubCategoryDTO;

import com.app.respository.SubCategoryRepo;

import com.app.entities.Category;

import com.app.entities.SubCategory;

import com.app.respository.CategoryRepo;


@Service
@Transactional

public class SubCategoryServiceImpl implements SubCategoryService {
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private SubCategoryRepo subCatRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public List<SubCategoryDTO> getAllSubCatFromCat(Long catId) {
		System.out.println("in service impl of subCat get");
		List<SubCategory> subCatList =subCatRepo.findByCategory(catId);
		return subCatList.stream()
				.map(subCat -> {SubCategoryDTO subCatDto=mapper.map(subCat, SubCategoryDTO.class);
				subCatDto.setCatId(catId);
				return subCatDto;})
				.collect(Collectors.toList());
	}

	@Override
	public String deleteSubCatDetails(Long subCatId) {
		SubCategory subCat=subCatRepo.findById(subCatId)
				.orElseThrow(()-> new ResourceNotFoundException("invalid subCat id"));
		subCatRepo.delete(subCat);
		return "SubCat Details deleted....";
	}

	@Override
	public SubCategoryDTO addNewSubCat(SubCategoryDTO dto) {
		Category cat = catRepo.findById(dto.getCatId())
				.orElseThrow(()->new ResourceNotFoundException("Invalid Cat id !!!"));
		SubCategory subCat = mapper.map(dto, SubCategory.class);
		subCat.setCategory(cat);
		
		SubCategoryDTO dto2= mapper.map(subCatRepo.save(subCat), SubCategoryDTO.class);
		dto2.setCatId(cat.getCategoryId());
		//return mapper.map(subCatRepo.save(subCat), SubCategoryDTO.class);
		return dto2;
	}

	@Override
	public SubCategoryDTO updateSubCat(Long subCatId, SubCategoryDTO dto) { 
		SubCategory subCat= subCatRepo.findById(subCatId)
				.orElseThrow(()->new ResourceNotFoundException("Invalid SubCat Id, SubCat not fount !!!"));
		SubCategory subCategory=mapper.map(dto, SubCategory.class);
		Category cat = catRepo.findById(dto.getCatId())
				.orElseThrow(()->new ResourceNotFoundException("Invalid Cat id !!!"));
		Long id=cat.getCategoryId();
		subCategory.setSubCatId(subCatId);
		subCategory.setCategory(cat);
		System.out.println(subCategory +"saa");
		subCatRepo.save(subCategory);
		
		SubCategoryDTO dto2= mapper.map(subCategory, SubCategoryDTO.class);
		dto2.setCatId(id);
		 return dto2;
	}

	@Override
	public SubCategoryDTO getSubCatDetails(Long catId, Long subCatId) {
		SubCategory subCat = subCatRepo.findById(subCatId)
				.orElseThrow(()-> new ResourceNotFoundException("invalid subCat id"));
		SubCategoryDTO dto2= mapper.map(subCat, SubCategoryDTO.class);
		dto2.setCatId(catId);
		return dto2;
	}

}