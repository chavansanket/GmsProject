package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Address;
import com.app.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/{addressId}")
	public ResponseEntity<?> getAddress(@PathVariable Long addressId){
		return ResponseEntity.status(HttpStatus.FOUND).body(addressService.findAddress(addressId));
	}
	
	@PostMapping
	public ResponseEntity<?> addAddress(@RequestBody Address address){
		System.out.println("in address controller ----"+address);
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(address));
	}
	
	@DeleteMapping("/{addressId}")
	public String deletAddress(@PathVariable Long addressId) {
		return addressService.deleteAddress(addressId);
	}
	
}
