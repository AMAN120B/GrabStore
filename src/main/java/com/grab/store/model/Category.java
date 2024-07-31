package com.grab.store.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
	@Id
	private Integer categoryId;
	private String categoryName;
	
	
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(Integer categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
