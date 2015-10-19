package org.gradle;

import static org.junit.Assert.*;
import org.junit.Test;


public class KvittoTest {
	
	
	@Test
	public void testActiveSales() {
		ReceiptMockClass sale = new ReceiptMockClass("Soda", 5);
		ReceiptMockClass prod = new ReceiptMockClass(10042, "Soda", 1, false, 15);
		
		assertEquals(10, (prod.getProductPrice()-sale.getDiscount()));
	}
	
	@Test
	public void testNewProduct() {
		ReceiptMockClass prod = new ReceiptMockClass(10042, "Soda", 1, false, 15);
		
		assertEquals(15, prod.getProductPrice());
	}
	
	@Test
	public void testNewCustomer() {
		ReceiptMockClass cust = new ReceiptMockClass("John Doe", "555 Main Street");
		
		assertEquals("John Doe", cust.getName());
		assertEquals("555 Main Street", cust.getAdress());
	}
	
	
	
	

}
