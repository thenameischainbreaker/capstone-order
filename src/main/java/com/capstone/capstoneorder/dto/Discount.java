package com.capstone.capstoneorder.dto;


public class Discount {
	
	private int d_id;
	private int p_id;

	private int userId;
	private double discount;
	
	public Discount() {}
	
	public Discount(int d_id, int p_id, int u_id, double discount)
	{
		this.d_id = d_id;
		this.p_id = p_id;
		this.userId = u_id;
		this.discount = discount;
	}

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Discount [d_id=" + d_id + ", p_id=" + p_id + ", userId=" + userId + ", discount=" + discount + "]";
	}
	
	
}