import static org.junit.Assert.*;

import org.junit.Test;

public class DiscountTester {
	
	@Test
	public void testPercent() {
		Discount discount = new DiscountPercent();
		int price = discount.apply(new OrderLine());
		assertEquals(0, price);
	}
}
