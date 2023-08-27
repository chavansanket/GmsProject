package com.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
}
