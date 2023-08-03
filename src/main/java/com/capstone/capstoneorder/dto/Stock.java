package com.capstone.capstoneorder.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Stock {
	
	private int p_id;
	private int s_quantity;
	
	public Stock() {}
	
	public Stock(int p_id, int s_quantity)
	{
		this.p_id = p_id;
		this.s_quantity = s_quantity;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public int getS_quantity() {
		return s_quantity;
	}

	public void setS_quantity(int s_quantity) {
		this.s_quantity = s_quantity;
	}


}
