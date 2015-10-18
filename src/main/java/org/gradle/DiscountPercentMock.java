package org.gradle;

import java.util.Date;

public class DiscountPercentMock extends DiscountMock {
	
	private int percent;  //Går att unvika avrundning?
	
	
	public DiscountPercentMock(Date startTime, Date endTime, int productID, int percent) {
		super(startTime, endTime, productID);
		
		if (percent < 0 || percent > 100)
			throw new IllegalArgumentException();
		
		this.percent = percent;
	}

	@Override
	public int apply(OrderLine orderLine) {
		
		//product.getID
		
		
		//Compare Discount.productID with orderLine.productID
		//If true return reduced price otherwise return normal price
		
		
		int price = orderLine.getProductPrice()*orderLine.getProductQuantity();
		if (productIDs.contains(orderLine.getProductID()))
			return (int)Math.ceil((float)(price*(100-percent))/100); //Discount
		else
			return price; //No discount
	}
	
}

