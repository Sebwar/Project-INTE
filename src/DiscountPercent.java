
public class DiscountPercent extends Discount {

	@Override
	public int apply(OrderLine orderLine) {
		//Compare Discount.productID with orderLine.productID
		//If true return reduced price otherwise return normal price
		return 0;
	}
}
