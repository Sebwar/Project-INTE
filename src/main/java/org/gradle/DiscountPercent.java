import java.util.Date;


public class DiscountPercent extends Discount {

	private int percent;
	
	public DiscountPercent(Date startTime, Date endTime, int productID, int percent) {
		super(startTime, endTime, productID);
		
		if (percent < 0 || percent > 100)
			throw new IllegalArgumentException();
		
		this.percent = percent;
	}

	@Override
	public int apply(OrderLine orderLine) {
		//Compare Discount.productID with orderLine.productID
		//If true return reduced price otherwise return normal price
		int price = 10;
		if (productIDs.contains(1))
			return price*percent/100; //Discount
		else
			return price; //No discount
	}
}
