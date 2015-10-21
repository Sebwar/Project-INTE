package org.gradle;

import static org.junit.Assert.*;
import org.junit.Test;


public class ReceiptTest {
		
	ReceiptMockClass testCustomer = new ReceiptMockClass ("org1", "Jor", "El", "Town", "The Street", "555 phone");
	
	
	@Test
	public void testNewProduct() {
		ReceiptMockClass prod = new ReceiptMockClass(10042, "Soda", 1, false, 15);
		assertEquals(10042, prod.getId());
		assertEquals("Soda", prod.getName());
		assertEquals(1, prod.getAmount());
		assertEquals(false, prod.getPriceByWeight());
		assertEquals(15, prod.getProductPrice());
	}
	
	@Test
	public void testNewCustomer() {
		ReceiptMockClass cust = new ReceiptMockClass("org123", "John", "Doe", "Central City", "555 Main Street", "555 123 456");
		assertEquals("org123", cust.getOrgNr());
		assertEquals("John", cust.getFirstName());
		assertEquals("Doe", cust.getLastName());
		assertEquals("Central City", cust.getAddress());
		assertEquals("555 Main Street", cust.getStreet());
		assertEquals("555 123 456", cust.getPhone());
	}
	
	@Test
	public void testConstructor() {
		
	}
	
	@Test
	public void testGetTotal() {
		ReceiptMockClass testProduct = new ReceiptMockClass (123, "Test", 2, false, 15);
		assertEquals(30, testProduct.getTotalCost());
	}
	
	
}