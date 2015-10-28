package org.gradle;

import java.util.ArrayList;

import org.gradle.discounts.Coupon;

public class Receipt {
	
	private Customer customer;
	private ArrayList<Coupon> coupons = new ArrayList<>();
	private ArrayList<OrderLine> orderLines = new ArrayList<>();
	
	public Receipt(Customer customer) {
		if(customer == null)
			throw new NullPointerException("Customer cannot be null.");
		this.customer = customer;
	}
	
	public Receipt () {
		this.customer = null;
	}
	
	public long getTotalPrice() {
		long totalCost = 0;
		for (OrderLine orderLine : orderLines)
			totalCost += orderLine.getTotalPrice();
		return totalCost;
	}
	
	public long getCouponReduction() {
		long couponReduction = 0;
		for (Coupon coupon : coupons)
			couponReduction += coupon.getCouponPrice();
		return couponReduction;
	}
	
	public void addCoupon(Coupon coupon) {
		coupons.add(coupon);
	}
	
	public void addOrderLine(OrderLine orderLine) {
		orderLines.add(orderLine);
	}
	
	public String toString() {
		String outputString = "";
		if (this.customer == null) {
			outputString += "New Sale:\n";
		}
		else {
			outputString += "New sale for: " + customer.getFullName() + "\n";
		}
		for (int i = 0; i<orderLines.size(); i++) {
				outputString += orderLines.get(i);
		}
		
		if (this.getCouponReduction() < 1) {
		outputString += "\nTotal price: " + this.getTotalPrice() + ":-";
		}
		else {
			outputString += "\nReduction from coupons: " + (this.getTotalPrice() - this.getCouponReduction()) +
					"\nTotal price after reduction: " +  this.getCouponReduction() + ":-";
		}
		return outputString;
	}
	
	
}