import java.util.Date;


public class DiscountPercent extends Discount {

	public DiscountPercent(Date startTime, Date endTime, int productID) {
		super(startTime, endTime, productID);
	}

	@Override
	public int apply(OrderLine orderLine) {
		//Compare Discount.productID with orderLine.productID
		//If true return reduced price otherwise return normal price
		if (productIDs.contains(1))
			return 1; //Discount
		else
			return 10; //No discount
	}
}
