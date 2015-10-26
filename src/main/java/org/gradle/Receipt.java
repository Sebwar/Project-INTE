package org.gradle;

import java.util.ArrayList;

import org.gradle.discounts.Coupon;

public class Receipt {
	
	private final Customer customer;
	private ArrayList<Coupon> coupons = new ArrayList<>();
	private ArrayList<OrderLine> orderLines = new ArrayList<>();
	
	public Receipt(Customer customer) {
		//Ska inte behöva ta en customer. En till konstruktor som inte tar customer.
		this.customer = customer;
	}
	
	public long getTotalPrice() {
		long totalCost = 0;
		for (OrderLine orderLine : orderLines)
			totalCost += orderLine.getTotalPrice();
		return totalCost;
	}
	
	public void addCoupon(Coupon coupon) {
		coupons.add(coupon);
	}
	
	public void addOrderLine(OrderLine orderLine) {
		orderLines.add(orderLine);
	}
	
	public void generateText() {
		
	}
}