import java.util.Date;


public class DiscountAmount extends Discount {

	public DiscountAmount(Date startTime, Date endTime, int productID) {
		super(startTime, endTime, productID);
	}

	@Override
	public int apply(OrderLine orderLine) {
		return 0;
	}

}
