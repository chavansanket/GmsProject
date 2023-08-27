
package com.app.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name = "Customer") 
@NoArgsConstructor
@Getter
@Setter
@ToString
//@EqualsAndHashCode(callSuper = false, doNotUseGetters = true,of = "email")
public class Customer {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	@Column(length = 30)
	private String firstName;
	@Column(length = 30)
	private String lastName;
	@Column(length = 30, unique = true) // =>unique
	private String email;
	@Column(nullable = false) // =>NOT NULL
	private String password;
	@Column(length = 13, unique = true)
	private String phone;
	

	
//	public Customer(String firstName, String lastName, String email, String password ,String phone,String address) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.password = password;
//		this.phone=phone;
//		this.address=address;
//	}
	
	
	
	public Customer(String firstName, String lastName, String email, String password ,String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone=phone;
	}
	
}


































//package com.app.entities;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.MapsId;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity 
//@Table(name = "customer") 
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
////@EqualsAndHashCode(callSuper = false, doNotUseGetters = true,of = "email")
//public class Customer {
//	@Id	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "customer_id")
//	private Long customerId;
//	
//	@Column(length = 30)
//	private String firstName;
//	@Column(length = 30)
//	private String lastName;
//	@Column(length = 30, unique = true) // =>unique
//	private String email;
//	@Column(nullable = false) // =>NOT NULL
//	private String password;
//	@Column(length = 13, unique = true)
//	private String phone;
//	
//	@OneToOne (fetch = FetchType.EAGER)//mandatory , o.w hib throws MappingExc
//	@JoinColumn(name="address_id")//optional : to specify name of FK col
//	//optional BUT reco : to use shared PK between Emp n Address
//	private Address address;
//	
//	
////	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
////	@JsonIgnore
////    private Cart cart;
//		
//	public Customer(String firstName, String lastName, String email, String password ,String phone) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.password = password;
//		this.phone=phone;
//	}
//	
//}
