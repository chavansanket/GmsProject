package com.sunbeaminfo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResp {	
	private Long id;
	private String firstName;	
	private String lastName;		
	private LocalDate joinDate;
	private double salary;	
	private String location;	
	private String department;	
}
