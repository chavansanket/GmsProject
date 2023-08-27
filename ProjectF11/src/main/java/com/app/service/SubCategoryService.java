package com.app.service;

import java.util.List;

import com.app.dto.SubCategoryDTO;

public interface SubCategoryService {
	
	List<SubCategoryDTO> getAllSubCatFromCat(Long catId);
	
	String deleteSubCatDetails(Long subCatId);
	
	SubCategoryDTO addNewSubCat(SubCategoryDTO dto);
	
	SubCategoryDTO updateSubCat(Long subCatId,SubCategoryDTO dto);
	
	SubCategoryDTO getSubCatDetails( Long catId,Long subCatId);
}