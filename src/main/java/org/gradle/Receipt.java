package org.gradle;

import java.util.ArrayList;

public class Receipt {
	
	private Customer customer;
	private Coupon coupon;
	private int totalCost;
	
	public ArrayList<OrderLine> orderLines = new ArrayList<>();
	
	public Receipt(Customer customer, Coupon coupon) {
		this.customer = customer;
		this.coupon = coupon;
	}
	
	for (Orderline orderLine : orderLines) {
		totalCost += orderLine.getTotalPrice();
	}}
}