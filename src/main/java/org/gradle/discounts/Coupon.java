package org.gradle.discounts;

import org.gradle.Money;

public class Coupon {
	private Money deduction;
	private Money requiredAmount;
	private Money amount;
	
	public Coupon(Money amount, Money deduction, Money requiredAmount) {
		this.amount = amount;
		this.deduction = deduction;
		this.requiredAmount = requiredAmount;
	}
	
	//Method to get total amount after coupon deduction
	public long getCouponPrice() {
		if (amount.compareTo(requiredAmount) < 0)
			throw new IllegalArgumentException("Total amount cannot be under 200.");
		
		return amount.subtract(deduction).getTotalAmountInMinorUnit();
	}
}
