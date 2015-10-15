package org.gradle;
import java.util.Date;
import java.util.ArrayList;


public abstract class Discount {
	
	protected Date startTime;
	protected Date endTime;
	protected ArrayList<Integer> productIDs;
	
	/* When creating a new Discount you need to know
	 * when it should start, when it should end and for what products it is for
	 * 
	 * productID cannot be negative
	 * endTime cannot be before startTime
	 */
	public Discount(Date startTime, Date endTime, int productID) {
		if (productID < 0)
			throw new IllegalArgumentException("Product ID cannot be negative.");
		
		if (endTime.getTime() < startTime.getTime())
			throw new IllegalArgumentException("End time cannot be earlier than start time.");
		
		productIDs = new ArrayList<Integer>();
		productIDs.add(productID);
	}
	
	public void addProduct(int productID) {
		if (productID < 0)
			throw new IllegalArgumentException();
		
		productIDs.add(productID);
	}
	
	public boolean isDiscounted(Product product) {
		return productIDs.contains(product.getID());
	}
	
	public abstract int apply(OrderLineMock orderLine);
}
