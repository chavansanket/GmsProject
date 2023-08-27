package com.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, Long>{

	
}
