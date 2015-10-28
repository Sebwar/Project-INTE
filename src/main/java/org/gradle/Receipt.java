package org.gradle;

import java.util.ArrayList;

import org.gradle.discounts.Coupon;

public class Receipt {
	
	private Customer customer;
	private ArrayList<Coupon> coupons = new ArrayList<>();
	private ArrayList<OrderLine> orderLines = new ArrayList<>();
	private Currency currency;
	
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
		if (currency == null)
			currency = orderLine.getCurrency();
		else if (orderLine.getCurrency() != currency)
			throw new IllegalArgumentException("Cannot add orderLine with different currency!");
		
		orderLines.add(orderLine);
	}
	
	public String toString() {
		String outputString = "";
		if (this.customer == null) {
			outputString += "New sale:\n";
		}
		else {
			outputString += "New sale for: " + customer.getFullName() + "\n";
		}
		for (int i = 0; i<orderLines.size(); i++) {
				outputString += orderLines.get(i) + "\n";
		}
		
		if (this.getCouponReduction() < 1) {
			outputString += "Total price: " + this.getTotalPrice()/100 + " " + currency.getSign();
		}
		else {
			outputString += "Reduction from coupons: " + (this.getTotalPrice() - this.getCouponReduction())/100 + " " + currency.getSign() +
					"\nTotal price after reduction: " +  this.getCouponReduction()/100 + " " + currency.getSign();
		}
		return outputString;
	}
	
	
}