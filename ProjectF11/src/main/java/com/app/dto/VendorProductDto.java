package com.app.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VendorProductDto {

	@JsonProperty(access = Access.READ_ONLY) //used during serialization
	private Long vendorProductId;
	
	private String productName;  //PID	PNAME	PRATE	PEXPDATE	PMANFACT	CATID
	
	private String productDesc;

	
	private LocalDate productMfgDate;
	
	private LocalDate productExpDate;
	
	
	private double productPrice ;
	
	private int productQuantity ;
	
	private String pmanufacturer;
	private Long categoryId;
	private Long vendorId;
	private Long subCatId;

}
