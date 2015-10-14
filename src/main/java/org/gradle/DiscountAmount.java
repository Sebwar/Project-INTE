package org.gradle;

import java.util.Date;


public class DiscountAmount extends Discount {

	private int requiredQuantity;
	private int reductionQuantity;
	
	public DiscountAmount(Date startTime, Date endTime, int productID, int requiredQuantity, int reductionQuantity) {
		super(startTime, endTime, productID);
		
		if (requiredQuantity < 2)
			throw new IllegalArgumentException("Required quantity cannot be less than 2.");
		
		if (reductionQuantity < 1)
			throw new IllegalArgumentException("Reduction quantity cannot be less than 1.");
		
		if (requiredQuantity < reductionQuantity)
			throw new IllegalArgumentException("Required quantity cannot be less than reduction quantity.");
		
		this.requiredQuantity = requiredQuantity;
		this.reductionQuantity = reductionQuantity;
	}

	@Override
	public int apply(OrderLine orderLine) {
		//Compare Discount.productID with orderLine.productID
		//If true return reduced price otherwise return normal price
		
		int productAmount = 3;
		int productPrice = 10;
		
		if (productIDs.contains(1))
			productAmount -= (productAmount / requiredQuantity) * reductionQuantity;
		
		return productAmount * productPrice;
	}

}
