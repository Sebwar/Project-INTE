package org.gradle;

import java.util.Date;

import org.gradle.discounts.Discount;


public class DiscountAmountMock extends Discount {

	private int requiredQuantity;
	private int reductionQuantity;
	
	public DiscountAmountMock(Date startTime, Date endTime, int itemID, boolean categoryDiscount, int requiredQuantity, int reductionQuantity) {
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

	public boolean isDiscounted(OrderLine orderLine) {
		if (orderLine.getProductQuantity() >= requiredQuantity)
			return super.isDiscounted(orderLine);
		
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

