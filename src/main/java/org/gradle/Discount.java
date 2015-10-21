package org.gradle;
import java.util.Date;
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
		
		if (endTime.getTime() < startTime.getTime())
			throw new IllegalArgumentException("End time cannot be earlier than start time.");
		
		itemIDs = new ArrayList<Integer>();
		itemIDs.add(itemID);
		this.categoryDiscount = categoryDiscount;
	}
	
	public boolean isDiscounted(int itemID, boolean category) {
		if (itemIDs.contains(itemID) && categoryDiscount == category)
			return true;
		else
			return false;
	}
	
	public void addItem(int itemID) {
		if (itemID < 0)
			throw new IllegalArgumentException();
		
		itemIDs.add(itemID);
	}
	
	public abstract int apply(OrderLine orderLine);
}
