package com.app.service;

import com.app.entities.Address;

public interface AddressService {
	
	Address addAddress(Address address);
	
	Address findAddress(Long addressId);
	
	Address updateAddress(Long addressId,Address address);
	
	String deleteAddress(Long addressId);
	
}
