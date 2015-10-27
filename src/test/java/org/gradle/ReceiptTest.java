package org.gradle;

import static org.junit.Assert.*;
import org.junit.Test;

public class ReceiptTest {
	
	private Customer testCustomer = new Customer("org123", "John", "Doe", "Central City", "555 Main Street", "555 123 456");
	private Product testProduct1 = new Product(555, "Horse", new Money(Currency.SEK, 101), false);
	private Product testProduct2 = new Product(404, "Eyepatch", new Money(Currency.SEK, 27), false);
	private Product testProduct3 = new Product(420, "Railgun", new Money(Currency.SEK, 12), false);
	private OrderLine testOrderLine1 = new OrderLine(testProduct1, 5);
	private OrderLine testOrderLine2 = new OrderLine(testProduct2, 4);
	private OrderLine testOrderLine3 = new OrderLine(testProduct3, 7);
	private Receipt receipt;
	
	@Test
	public void testCunstructorWithoutCustomer() {
		receipt = new Receipt();
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorWithNullParameter() {
		receipt = new Receipt(null);
	}
	
	@Test
	public void testConstructorWithCustomer() {
		receipt = new Receipt(testCustomer);
	}
	
	@Test
	public void testGetTotalPriceNoOrderLine() {
		receipt = new Receipt();
		assertEquals(0, receipt.getTotalPrice());
	}
	
	@Test
	public void testGetTotalPriceSingleOrderLine() {
		receipt = new Receipt();
		receipt.addOrderLine(testOrderLine1);
		assertEquals(505, receipt.getTotalPrice());
	}
	
	@Test
	public void testGetTotalPriceMultipleOrderLines() {
		receipt = new Receipt();
		receipt.addOrderLine(testOrderLine1);
		receipt.addOrderLine(testOrderLine2);
		receipt.addOrderLine(testOrderLine3);
		assertEquals(697, receipt.getTotalPrice());
	}
		
}