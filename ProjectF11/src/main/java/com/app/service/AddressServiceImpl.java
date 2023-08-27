package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.Address;
import com.app.respository.AddressRepo;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo addressRepo;
	
	@Override
	public Address addAddress(Address address) {	
		System.out.println("add address in Service---"+address);
		return addressRepo.save(address);
	}

	@Override
	public Address findAddress(Long addressId) {
		return addressRepo.findByAddressId(addressId);
	}

	@Override
	public Address updateAddress(Long addressId, Address address) {
		address.setAddressId(addressId);
		return addressRepo.save(address);
	}

	@Override
	public String deleteAddress(Long addressId) {
		addressRepo.deleteById(addressId);
		return "Address deleted";
	}

}
