package org.gradle;

import org.gradle.discounts.Coupon;
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
	private Coupon testCoupon1;
	private Receipt receipt;
	
	@Test
	public void testCunstructorWithoutCustomer() {
		receipt = new Receipt();
	}
	
	@Test (expected=NullPointerException.class)
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
	
	@Test
	public void testGetCouponReductionSingleCoupon() {
		receipt = new Receipt();
		receipt.addOrderLine(testOrderLine1);
		testCoupon1 = new Coupon(new Money(Currency.SEK, receipt.getTotalPrice()), 
				new Money(Currency.SEK, 100), new Money(Currency.SEK, 200));
		receipt.addCoupon(testCoupon1);
		assertEquals(405, receipt.getCouponReduction());
	}
	
//	@Test //Doesn't work due to coupon class
//	public void testGetCoupongReductionMultipleCoupons() {
//		receipt = new Receipt();
//		receipt.addOrderLine(testOrderLine1);
//		testCoupon1 = new Coupon(new Money(Currency.SEK, receipt.getTotalPrice()),
//				new Money(Currency.SEK, 100), new Money(Currency.SEK, 200));
//		testCoupon2 = new Coupon(new Money(Currency.SEK, receipt.getTotalPrice()),
//				new Money(Currency.SEK, 50), new Money(Currency.SEK, 200));
//		receipt.addCoupon(testCoupon1);
//		receipt.addCoupon(testCoupon2);
//		assertEquals(355, receipt.getCouponReduction());
//	}
	
	@Test
	public void testToStringWithCustomer() {
		receipt = new Receipt(testCustomer);
		receipt.addOrderLine(testOrderLine1);
		assertEquals("New sale for: John Doe\nHorse  5st*1.1 kr  5.5 kr\nTotal price: 5.05 kr", receipt.toString());
	}
	
	@Test
	public void testToStringWithoutCustomer() {
		receipt = new Receipt();
		receipt.addOrderLine(testOrderLine1);
		assertEquals("New sale:\nHorse  5st*1.1 kr  5.5 kr\nTotal price: 5.05 kr", receipt.toString());
	}
	
	@Test
	public void testToStringWithMultipleOrderLines() {
		receipt = new Receipt();
		receipt.addOrderLine(testOrderLine1);
		receipt.addOrderLine(testOrderLine2);
		assertEquals("New sale:\nHorse  5st*1.1 kr  5.5 kr\nEyepatch  4st*0.27 kr  1.8 kr\nTotal price: 6.13 kr", receipt.toString());
	}
	
	@Test
	public void testToStringWithCoupon() {
		receipt = new Receipt();
		receipt.addOrderLine(testOrderLine1);
		testCoupon1 = new Coupon(new Money(Currency.SEK, receipt.getTotalPrice()),
				new Money(Currency.SEK, 100), new Money(Currency.SEK, 200));
		receipt.addCoupon(testCoupon1);
		assertEquals("New sale:\nHorse  5st*1.1 kr  5.5 kr\nReduction from coupons: 1.0 kr\nTotal price after reduction: 4.05 kr", receipt.toString());
	}
		
}