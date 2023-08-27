package com.app.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class addNewCustomerDto {
	
	@JsonProperty(access = Access.READ_ONLY) // used during serialization
	private Long id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Email
	private String email;
	private String password;
	private String phone;
	private String confirmPassword;
	
	//*****NO Ctor********
//	public addNewUserDto(String firstName, String lastName, String email, String password ,String phone,String confirmPassword) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.phone = phone;
//		this.password = password;
//		this.confirmPassword = confirmPassword;
//	}
//	
//	public addNewUserDto() {
//		
//	}
}
