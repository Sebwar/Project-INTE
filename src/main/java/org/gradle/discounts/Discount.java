package org.gradle.discounts;
import java.util.Date;

import org.gradle.Category;
import org.gradle.OrderLine;

import java.util.ArrayList;


public abstract class Discount {
	
	protected Date startTime;
	protected Date endTime;
	protected ArrayList<Integer> itemIDs;
	protected boolean categoryDiscount;
	
	/* When creating a new Discount you need to know
	 * when it should start, when it should end and for what products it is for
	 * 
	 * itemID cannot be negative
	 * endTime cannot be before startTime
	 */
	public Discount(Date startTime, Date endTime, int itemID, boolean categoryDiscount) {
		if (itemID < 0)
			throw new IllegalArgumentException("Product/Category ID cannot be negative.");
		
		if (endTime.before(startTime))
			throw new IllegalArgumentException("End time cannot be earlier than start time.");
		
		itemIDs = new ArrayList<Integer>();
		itemIDs.add(itemID);
		this.categoryDiscount = categoryDiscount;
	}
	
	public boolean isDiscounted(OrderLine orderLine) {
		if (categoryDiscount) {
			//Loops through all categories and checks if orderLine.productID is part of one of those categories
			for (Integer id : itemIDs) {
				System.out.println(id);
				if (Category.getCategoryByID(id).inCategory(orderLine.getProductID()))
					return true;
			}
		} else if (!categoryDiscount && itemIDs.contains(orderLine.getProductID()))
			return true;
		
		return false;
	}
	
	public void addItem(int itemID) {
		if (itemID < 0)
			throw new IllegalArgumentException();
		
		itemIDs.add(itemID);
	}
	
	public abstract long apply(OrderLine orderLine);
}
