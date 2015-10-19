package org.gradle;

public class Coupon {
	private static final int couponDeduct = 20;
	private static final int couponMinAmount = 200;
	private int amount = 0;
	
	//Construct
	public Coupon(int amount) {
		this.amount = amount;
	}
	
	//Method to get total amount after coupon deduction
	public int getCouponPrice() {
		if (amount < 0)
			throw new IllegalArgumentException("Total amount cannot be negative.");
		
		if (amount >= couponMinAmount)
			throw new IllegalArgumentException("Total amount cannot be under 200.");
		
		return amount - couponDeduct;
	}
}
