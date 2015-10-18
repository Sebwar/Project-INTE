package org.gradle;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderLineTest {
	
	Product tk = new Product(240, "Tandkr�m", 25, false);
	Product tb = new Product(67, "Tandborste", 35, false);
	Product k = new Product(58, "Kaffe", 14, true);
	Product h = new Product(463, "H�grev", 16, true);

	OrderLine o1s = new OrderLine(tk, 2);
	OrderLine o2s = new OrderLine(tb, 4);
	OrderLine o1k = new OrderLine(k, 50);
	OrderLine o2k = new OrderLine(h, 150);
	
	@Test
	public void testConstructorStyck() {//pris i �re/st
		
		assertEquals(50, o1s.getTotalPrice());
		assertEquals(140, o2s.getTotalPrice());
	}
	@Test
	public void testConstructorKilo(){//pris i �re/g
		
		assertEquals(700, o1k.getTotalPrice());
		assertEquals(2400, o2k.getTotalPrice());
	}
	@Test
	public void testTostringStyck(){
		
		assertEquals("ProduktID 240  2st*25�re  50�re", o1s.toString());
		assertEquals("ProduktID 67  4st*35�re  140�re", o2s.toString());
	}
	
	@Test
	public void testTostringKilo(){
		
		assertEquals("ProduktID 58  50g*14�re/g  700�re", o1k.toString());
		assertEquals("ProduktID 463  150g*16�re/g  2400�re", o2k.toString());
		
	}
	
	
}
