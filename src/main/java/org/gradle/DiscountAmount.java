import java.util.Date;


public class DiscountAmount extends Discount {

	private int reqAmount;
	private int redAmount;
	
	public DiscountAmount(Date startTime, Date endTime, int productID, int reqAmount, int redAmount) {
		super(startTime, endTime, productID);
		
		if (reqAmount < 0 || redAmount < 0 || reqAmount < redAmount)
			throw new IllegalArgumentException();
		
		this.reqAmount = reqAmount;
		this.redAmount = redAmount;
	}

	@Override
	public int apply(OrderLine orderLine) {
		//Compare Discount.productID with orderLine.productID
		//If true return reduced price otherwise return normal price
		
		int productAmount = 3;
		int productPrice = 10;
		
		if (productIDs.contains(1))
			productAmount -= (productAmount / reqAmount) * redAmount;
		
		return productAmount * productPrice;
	}

}
