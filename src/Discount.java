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
			throw new IllegalArgumentException();
		
		if (endTime.getTime() < startTime.getTime())
			throw new IllegalArgumentException();
		
		productIDs = new ArrayList<Integer>();
		productIDs.add(productID);
	}
	
	public abstract int apply(OrderLine orderLine);
}
