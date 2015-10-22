package org.gradle;


import org.junit.Test;
import static org.junit.Assert.*;

public class OrderLineTest {
	
	Product tk = new Product(240, "Tandkräm", 25, false);
	Product tb = new Product(67, "Tandborste", 35, false);
	Product tt = new Product(569, "Trädgårdstomte", 1500, false);
	
	Product k = new Product(58, "Kaffe", 14, true);
	Product h = new Product(463, "Högrev", 16, true);

	OrderLine o1s = new OrderLine(tk, 2);
	OrderLine o2s = new OrderLine(tb, 4);
	OrderLine ots = new OrderLine(tt, 1);
	OrderLine o1k = new OrderLine(k, 50);
	OrderLine o2k = new OrderLine(h, 150);
	
	java.util.Date d1 = new java.util.Date(670);
	java.util.Date d2 = new java.util.Date(875600);
	
	@Test
	public void testConstructorStyck() {//pris i öre/st
		
		assertEquals(50, o1s.getTotalPrice());
		assertEquals(140, o2s.getTotalPrice());
	}
	@Test
	public void testConstructorKilo(){//pris i öre/g
		
		assertEquals(700, o1k.getTotalPrice());
		assertEquals(2400, o2k.getTotalPrice());
	}
	@Test
	public void testTostringStyck(){
		
		assertEquals("ProduktID 240  2st*0.25 kr  0.50 kr", o1s.toString());
		assertEquals("ProduktID 569  1st*15.0 kr  15.0 kr", ots.toString());
	}
	
	@Test
	public void testTostringKilo(){
		
		assertEquals("ProduktID 58  0.05kg*140.0 kr/kg  7.0 kr", o1k.toString());
		assertEquals("ProduktID 463  0.15kg*160.0 kr/kg  24.0 kr", o2k.toString());
		
	}
	@Test
	public void testDiscountPercentStyck(){
		
		Discount disc = new DiscountPercentMock(d1, d2, 67, false, 30);
		o2s.addDiscount(disc);
		
		assertEquals(50, o1s.getTotalPrice());
		assertEquals(98, o2s.getTotalPrice());
		
		assertEquals("ProduktID 240  2st*0.25 kr  0.50 kr", o1s.toString());
		assertEquals("ProduktID 67  4st*0.35 kr  1.40 kr\n..30% discount -0.42 kr", o2s.toString());
		
		o2s.removeDiscount(disc);
		
	}
	@Test
	public void testDiscountPercentKilo(){
		
		Discount disc = new DiscountPercentMock(d1, d2, 463, false, 35);
		o2k.addDiscount(disc);
		
		assertEquals(700, o1k.getTotalPrice());
		assertEquals(1560, o2k.getTotalPrice());
		
		assertEquals("ProduktID 58  0.05kg*140.0 kr/kg  7.0 kr", o1k.toString());
		assertEquals("ProduktID 463  0.15kg*160.0 kr/kg  24.0 kr\n..35% discount -8.40 kr", o2k.toString());
		
		o2k.removeDiscount(disc);
		
	}
	@Test
	public void testDiscountAmountStyck(){
		
		OrderLine o3s = new OrderLine(tk, 5);
		
		Discount disc = new DiscountAmountMock(d1, d2, 240, false, 4, 1);
		o1s.addDiscount(disc);
		
		assertEquals(50, o1s.getTotalPrice());
		assertEquals(100, o3s.getTotalPrice());
		
		
		//assertEquals("ProduktID 240  2st*0.25 kr  0.50 kr", o2s.toString());
		//assertEquals("ProduktID 240  5st*0.25 kr  1.25 kr\n..Rabatt:4 för 3  -0,25kr", o2s.toString());
	}
	
	
	
}
