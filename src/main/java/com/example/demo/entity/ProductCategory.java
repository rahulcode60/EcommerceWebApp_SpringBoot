package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productcategory")
public class ProductCategory {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productcategoryid")
    private Long productCategoryId;

    @Column(name = "productcategoryname")
    private String productCategoryName;

	public ProductCategory() {
	}

	public ProductCategory(Long productCategoryId, String productCategoryName) {
		super();
		this.productCategoryId = productCategoryId;
		this.productCategoryName = productCategoryName;
	}

	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	@Override
	public String toString() {
		return "ProductCategory [productCategoryId=" + productCategoryId + ", productCategoryName="
				+ productCategoryName + "]";
	}
	
    
}
