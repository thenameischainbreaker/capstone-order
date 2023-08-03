package com.capstone.capstoneorder;

import java.sql.Date;
import java.sql.Timestamp;
//import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int o_id;
	@Column(name="u_id")
	private int userId;
	@Column (name="p_id")
	private int productId;
	@Column(name = "o_quantity")
	private int quantity;
	@Column(name = "o_date")
	
	private Date date;
	private double o_price;
	private int d_id;
	
	public Order() {}

	public Order(int o_id, int userId, int p_id, int quantity, Date date, double o_price, int d_id) {
		super();
		this.o_id = o_id;
		this.userId = userId;
		this.productId = p_id;
		this.quantity = quantity;
		this.date = date;
		this.o_price = o_price;
		this.d_id = d_id;
	}

	public int getO_id() {
		return o_id;
	}

	public void setO_id(int o_id) {
		this.o_id = o_id;
	}

	public int getuserId() {
		return userId;
	}

	public void setuserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int p_id) {
		this.productId = p_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getO_price() {
		return o_price;
	}

	public void setO_price(double o_price) {
		this.o_price = o_price;
	}

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	@Override
	public String toString() {
		return "Order [o_id=" + o_id + ", userId=" + userId + ", p_id=" + productId + ", quantity=" + quantity + ", date=" + date
				+ ", o_price=" + o_price + ", d_id=" + d_id + "]";
	}
	


}
