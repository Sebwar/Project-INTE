package org.gradle;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderLineTest {

	@Test
	public void testConstructorStyck() {
		Product p = new Product(240, "Tandkräm", 25, false);
		OrderLine o = new OrderLine(p, 2);
		
		assertEquals(50, o.getTotalPrice());
			
	}
	
	public void testConstructorKilo(){
		Product p = new Product(58, "Kaffe", 84, true);
		OrderLine o = new OrderLine(p, 0.5);
	
		assertEquals(42, o.getTotalPrice());
		
	}
	
}
