package com.capstone.capstoneorder.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Product {
	
	private int p_id;
	private String p_name;
	private String image_id;
	private double p_price;
	private String p_description;
	
	public Product() {}
	
	public Product(int p_id, String p_name, String image_id, double p_price, String p_description)
	{
		this.p_id = p_id;
		this.p_name = p_name;
		this.image_id = image_id;
		this.p_price = p_price;
		this.p_description = p_description;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public double getP_price() {
		return p_price;
	}

	public void setP_price(double p_price) {
		this.p_price = p_price;
	}

	public String getP_description() {
		return p_description;
	}

	public void setP_description(String p_description) {
		this.p_description = p_description;
	}

	@Override
	public String toString() {
		return "Product [p_id=" + p_id + ", p_name=" + p_name + ", image_id=" + image_id + ", p_price=" + p_price
				+ ", p_description=" + p_description + "]";
	}
	
}
