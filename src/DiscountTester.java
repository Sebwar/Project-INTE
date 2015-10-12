import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DiscountTester {
	
	private static final Date VALID_START_TIME = new Date();
	private static final Date VALID_END_TIME = new Date();
	private static final int VALID_PRODUCT_ID = 0;
	private static final int VALID_DISCOUNT_PRODUCT_ID = 1;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	@Test
	public void testPercent() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID);
		int price = discount.apply(new OrderLine());
		assertEquals(10, price);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeProduct() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidDate() {
		Discount discount = new DiscountPercent(new Date(1), new Date(0), VALID_PRODUCT_ID);
	}
	
	@Test
	public void testReducedPrice() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_DISCOUNT_PRODUCT_ID);
		int price = discount.apply(new OrderLine());
		assertEquals(1, price);
	}
}
