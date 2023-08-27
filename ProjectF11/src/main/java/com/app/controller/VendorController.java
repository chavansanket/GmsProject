package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddNewCustomerDto;
import com.app.dto.AddVendorDto;
import com.app.service.VendorService;

@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "*")
public class VendorController {
	
	@Autowired
	VendorService vendorService;

	
	@GetMapping
	public ResponseEntity<?> vendorList(){
		return ResponseEntity.ok(vendorService.getAllVendor());
	}
	
	@PostMapping
	public ResponseEntity<?> addVendor(@RequestBody AddVendorDto dto){
		System.out.println("dto in vendor controller ---"+ dto);
		return ResponseEntity.ok(vendorService.addNewVendo(dto));
	}

	@GetMapping("/{vendorId}")
	public ResponseEntity<?> findVendorByiId(@PathVariable Long vendorId){
		return ResponseEntity.ok(vendorService.getVendorById(vendorId));

	}

}
