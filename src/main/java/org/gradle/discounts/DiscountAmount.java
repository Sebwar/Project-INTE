package org.gradle.discounts;

import java.util.Date;

import org.gradle.OrderLine;


public class DiscountAmount extends Discount {

	private int requiredQuantity;
	private int reductionQuantity;
	
	public DiscountAmount(Date startTime, Date endTime, int itemID, boolean categoryDiscount, int requiredQuantity, int reductionQuantity) {
		super(startTime, endTime, itemID, categoryDiscount);
		
		if (requiredQuantity < 2)
			throw new IllegalArgumentException("Required quantity cannot be less than 2.");
		
		if (reductionQuantity < 1)
			throw new IllegalArgumentException("Reduction quantity cannot be less than 1.");
		
		if (requiredQuantity < reductionQuantity)
			throw new IllegalArgumentException("Required quantity cannot be less than reduction quantity.");
		
		this.requiredQuantity = requiredQuantity;
		this.reductionQuantity = reductionQuantity;
	}
	
	public String toString() {
		return "Buy " + requiredQuantity + " pay for " + (requiredQuantity-reductionQuantity);
	}

	public boolean isDiscounted(OrderLine orderLine, boolean category) {
		if (itemIDs.contains(orderLine.getProductID()) && orderLine.getProductQuantity() >= requiredQuantity && categoryDiscount == category)
			return true;
		else
			return false;
	}
	
	@Override
	public long apply(OrderLine orderLine) {
		//Compare Discount.productID with orderLine.productID
		//If true return reduced price otherwise return normal price
		
		int productAmount = orderLine.getProductQuantity();
		long productPrice = orderLine.getProductPrice();
		
		if (itemIDs.contains(orderLine.getProductID()))
			productAmount -= (productAmount / requiredQuantity) * reductionQuantity;
		
		return productAmount * productPrice;
	}

}
