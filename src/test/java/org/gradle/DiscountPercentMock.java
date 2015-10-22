package org.gradle;

import java.util.Date;

import org.gradle.discounts.Discount;


public class DiscountPercentMock extends Discount {

	private int percent;
	
	public DiscountPercentMock(Date startTime, Date endTime, int itemID, boolean categoryDiscount, int percent) {
		super(startTime, endTime, itemID, categoryDiscount);
		
		if (percent < 0)
			throw new IllegalArgumentException("Percent cannot be negative");
		
		if (percent > 100)
			throw new IllegalArgumentException("Percent cannot be more than 100");
		
		this.percent = percent;
	}
	
	public String toString() {
		return percent+"% discount";
	}

	@Override
	public long apply(OrderLine orderLine) {
		//Compare Discount.productID with orderLine.productID
		//If true return reduced price otherwise return normal price
		long price = orderLine.getProductPrice()*orderLine.getProductQuantity();
		if (itemIDs.contains(orderLine.getProductID()))
			return (int)Math.ceil((float)(price*(100-percent))/100); //Discount
		else
			return price; //No discount
	}
		
}

