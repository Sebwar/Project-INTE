package org.gradle;

import static org.junit.Assert.*;
import org.junit.Test;


public class KvittoTest {
	
	
	@Test
	public void testActiveSales() {
		KvittoMockClass sale = new KvittoMockClass("Soda", 5);
		KvittoMockClass prod = new KvittoMockClass(10042, "Soda", 1, false, 15);
		
		assertEquals(10, (prod.getProductPrice()-sale.getDiscount()));
	}
	
	@Test
	public void testNewProduct() {
		KvittoMockClass prod = new KvittoMockClass(10042, "Soda", 1, false, 15);
		
		assertEquals(15, prod.getProductPrice());
	}
	
	@Test
	public void testNewCustomer() {
		KvittoMockClass cust = new KvittoMockClass("John Doe", "555 Main Street");
		
		assertEquals("John Doe", cust.getName());
		assertEquals("555 Main Street", cust.getAdress());
	}
	
	
	
	

}
