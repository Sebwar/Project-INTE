package org.gradle;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderLineTest {

	@Test
	public void testConstructorStyck() {
		Product p = new Product(240, "Tandkräm", 25, false);
		OrderLine ol = new OrderLine(p, 2);
		
		assertEquals(50, ol.getTotalPrice());
			
	}
	
	public void testConstructorKilo(){
		Product p = new Product(58, "Kaffe", 14, true);
		OrderLine ol = new OrderLine(p, 50);
	
		assertEquals(700, ol.getTotalPrice());
		
	}
	
}
