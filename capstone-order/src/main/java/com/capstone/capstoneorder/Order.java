package com.capstone.capstoneorder;

import java.sql.Date;
//import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int o_id;
	private int u_id;
	private int p_id;
	private int quantity;
	@Column(name = "o_date")
	private Date date;
	private double o_price;
	private int d_id;
	
	public Order() {}
	
	public Order(int o_id, int u_id, int p_id, int quantity, Date date, double o_price, int d_id)
	{
		this.o_id = o_id;
		this.u_id = u_id;
		this.p_id = p_id;
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

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
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
}
