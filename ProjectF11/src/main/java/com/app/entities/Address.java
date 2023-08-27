package com.app.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="address")
@Getter
@Setter
@ToString
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private Long addressId;
	@Column(name="adr_line1",length=100)
	private String adrLine1;
	@Column(name="adr_line2",length=100)
	private String adrLine2;
	@Column(length=20)
	private String city;
	@Column(length=20)
	private String state;
	@Column(length=20)
	private String country;
	@Column(length=20,name="zip_code")
	private String zipCode;
	//one-to-one , uni dir Address 1--->1 Employee
	//owning side : Address (since FK)
//	@OneToOne (fetch = FetchType.LAZY)//mandatory , o.w hib throws MappingExc
//	@JoinColumn(name="customerId")//optional : to specify name of FK col
//	@MapsId//optional BUT reco : to use shared PK between Emp n Address
//	private Customer customer;
//	public Address() {
//		System.out.println("in def ctor of "+getClass());
//	}
//	public Address(String adrLine1, String adrLine2, String city, String state, String country, String zipCode) {
//		super();
//		this.adrLine1 = adrLine1;
//		this.adrLine2 = adrLine2;
//		this.city = city;
//		this.state = state;
//		this.country = country;
//		this.zipCode = zipCode;
//	}
	
	
}
