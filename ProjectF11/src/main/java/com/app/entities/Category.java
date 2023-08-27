package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name = "Category") 
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"products","subCategoryList"})
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Long categoryId;
	
	@Column(name="category_name",length =20)
	private String categoryName;
	
	@Column(name="category_desc",length=250)
	private String categoryDesc;
	
	@OneToMany(mappedBy = "category", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true /* , fetch = FetchType.EAGER */ )
	@JsonIgnore //To tell Jackson : ignore this property during ser n de-ser.
	//@JsonIgnoreProperties 
	private List<VendorProducts> products = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="category",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<SubCategory> subCategoryList = new ArrayList<SubCategory>();
	
	public void addSubCategory (SubCategory sC) {
		subCategoryList.add(sC);
		sC.setCategory(this);
	}
	
	public void removeSubCategory(SubCategory sC) {
		subCategoryList.remove(sC);
		sC.setCategory(null);
	}
	
	public void addProduct(VendorProducts p) {
		products.add(p); //cat--->prod
		p.setCategory(this); //prod--->cat
	}
	
//	public void removeProduct(VendorProducts p) {
//		products.remove(p);
//		p.setCategory(null);
//	}
	
	
}
