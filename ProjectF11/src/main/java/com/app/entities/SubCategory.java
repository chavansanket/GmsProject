package com.app.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.entities.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name = "Sub_Category") 
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"category","products"})

public class SubCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subCat_id")
	private Long subCatId;
	
	@Column(name="subCat_name",length =20)
	private String subCatName;
	
	@Column(name="subCategory_desc",length=250)
	private String subCategoryDesc;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinColumn(name="cat_Id")
	private Category category;
	
	@OneToMany(mappedBy = "subcategory", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true /* , fetch = FetchType.EAGER */ )
//	@JsonIgnore //To tell Jackson : ignore this property during ser n de-ser.
	//@JsonIgnoreProperties 
	private List<VendorProducts> products = new ArrayList<>();
	
	public void addProducts(VendorProducts p) {
		products.add(p);// dept --> emp
		p.setSubcategory(this);// emp --> dept
	}
}