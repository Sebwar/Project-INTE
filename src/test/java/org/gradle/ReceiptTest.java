package org.gradle;

import static org.junit.Assert.*;
import org.junit.Test;


public class ReceiptTest {
	
	ReceiptMock prod = new ReceiptMock(10042, "Soda", 1, false, 15);
	ReceiptMock cust = new ReceiptMock("org123", "John", "Doe", "Central City", "555 Main Street", "555 123 456");
	
	//Tests construction of a new mock product
	@Test
	public void testNewProductID() {
		assertEquals(10042, prod.getId());
	}
	
	@Test
	public void testNewProductName() {
		assertEquals("Soda", prod.getName());
	}
	
	@Test
	public void testNewProductAmount() {
		assertEquals(1, prod.getAmount());
	}
	
	@Test
	public void testNewProductPriceByWeight() {
		assertEquals(false, prod.getPriceByWeight());
	}
	
	@Test
	public void testNewProductPrice() {
		assertEquals(15, prod.getProductPrice());
	}
	
	//Tests construction of a new mock customer
	@Test
	public void testNewCustomerOrgNr() {
		assertEquals("org123", cust.getOrgNr());
	}
	
	@Test
	public void testNewCustomerFirstName() {
		assertEquals("John", cust.getFirstName());
	}
	
	@Test
	public void testNewCustomerLastName() {
		assertEquals("Doe", cust.getLastName());
	}
	
	@Test
	public void testNewCustomerAddress() {
		assertEquals("Central City", cust.getAddress());
	}
	
	@Test
	public void testNewCustomerStreet() {
		assertEquals("555 Main Street", cust.getStreet());
	}
	
	@Test
	public void testNewCustomerPhone() {
		assertEquals("555 123 456", cust.getPhone());
	}
	
	@Test
	public void testGetTotal() {
		ReceiptMock testProduct = new ReceiptMock(123, "Test", 2, false, 15);
		assertEquals(30, testProduct.getTotalCost());
	}
	
	@Test
	public void testNewCoupon() {
		ReceiptMock testCoupon = new ReceiptMock(50);
			assertEquals(50, testCoupon.getAmount());
	}
	
	@Test
	public void testCouponDeduction() {
		ReceiptMock newTestCoupon = new ReceiptMock(40);
		assertEquals(-40, newTestCoupon.getTotalCost());
	}
	
}