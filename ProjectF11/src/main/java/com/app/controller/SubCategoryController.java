package com.app.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dto.SubCategoryDTO;
import com.app.service.SubCategoryService;


@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {
	@Autowired
	private SubCategoryService subCategoryService;
	
	@PostMapping
	public ResponseEntity<?> addSubCatToExistingCat(@RequestBody @Valid SubCategoryDTO dto){
		System.out.println("in add to subCat" + dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(subCategoryService.addNewSubCat(dto));
	}
	
	@GetMapping("/categories/{catId}")
	public ResponseEntity<?> getSubCatsByCategory(@PathVariable Long catId){
		System.out.println("in get of subCats"+ catId);
		List<SubCategoryDTO> list = subCategoryService.getAllSubCatFromCat(catId);
		if(list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				//subCat found
			return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/{subCatId}")
	public ResponseEntity<?> deleteSubCat(@PathVariable Long subCatId){
		return ResponseEntity.status(HttpStatus.CREATED).body(subCategoryService.deleteSubCatDetails(subCatId));
	}
	
	@PutMapping("/{subCatId}")
	public ResponseEntity<?> updateSubCat(@RequestBody @Valid SubCategoryDTO dto, @PathVariable Long subCatId){
		System.out.println("in update to subCat" + dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(subCategoryService.updateSubCat(subCatId, dto));
	}
	
	
}