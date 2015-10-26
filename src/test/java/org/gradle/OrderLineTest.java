package org.gradle;


import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.gradle.discounts.Discount;
import org.junit.Test;

public class OrderLineTest {
	
	Product product1 = new Product(240, "Tandkräm", new Money(Currency.SEK, 25), false);
	Product product2 = new Product(67, "Tandborste", new Money(Currency.EUR, 35), false);
	Product product3 = new Product(569, "Trädgårdstomte", new Money(Currency.USD, 1500), false);
	
	Product weightedProduct1 = new Product(58, "Kaffe", new Money(Currency.SEK, 14), true);
	Product weightedProduct2 = new Product(463, "Högrev", new Money(Currency.USD, 16), true);

	OrderLine orderLine1 = new OrderLine(product1, 2);
	OrderLine orderLine2 = new OrderLine(product2, 4);
	OrderLine orderLine3 = new OrderLine(product3, 1);
	OrderLine orderLineWeighted1 = new OrderLine(weightedProduct1, 50);
	OrderLine orderLineWeighted2 = new OrderLine(weightedProduct2, 150);
	
	Date date1 = new Date(670);
	Date date2 = new Date(875600);
	
	@Test
	public void testConstructorStyck() {//pris i öre/st
		
		assertEquals(50, orderLine1.getTotalPrice());
		assertEquals(140, orderLine2.getTotalPrice());
	}
	@Test
	public void testConstructorKilo(){//pris i öre/g
		
		assertEquals(700, orderLineWeighted1.getTotalPrice());
		assertEquals(2400, orderLineWeighted2.getTotalPrice());
	}
	@Test
	public void testTostringStyck(){
		
		assertEquals("Tandkräm  2st*0.25 kr  0.50 kr", orderLine1.toString());
		assertEquals("Trädgårdstomte  1st*$ 15.0  $ 15.0", orderLine3.toString());
	}
	
	@Test
	public void testTostringKilo(){
		
		assertEquals("Kaffe  0.05kg*140.0 kr/kg  7.0 kr", orderLineWeighted1.toString());
		assertEquals("Högrev  0.15kg*$ 160.0/kg  $ 24.0", orderLineWeighted2.toString());
		
	}
	@Test
	public void testDiscountPercentStyck(){
		
		Discount disc = new DiscountPercentMock(date1, date2, 67, false, 30);
		orderLine2.addDiscount(disc);
		
		assertEquals(50, orderLine1.getTotalPrice());
		assertEquals(98, orderLine2.getTotalPrice());
		
		assertEquals("Tandkräm  2st*0.25 kr  0.50 kr", orderLine1.toString());
		assertEquals("Tandborste  4st*€ 0.35  € 1.40\n..30% discount -€ 0.42", orderLine2.toString());
		
		orderLine2.removeDiscount(disc);
		
	}
	@Test
	public void testDiscountPercentKilo(){
		
		Discount disc = new DiscountPercentMock(date1, date2, 463, false, 35);
		orderLineWeighted2.addDiscount(disc);
		
		assertEquals(700, orderLineWeighted1.getTotalPrice());
		assertEquals(1560, orderLineWeighted2.getTotalPrice());
		
		assertEquals("Kaffe  0.05kg*140.0 kr/kg  7.0 kr", orderLineWeighted1.toString());
		assertEquals("Högrev  0.15kg*$ 160.0/kg  $ 24.0\n..35% discount -$ 8.40", orderLineWeighted2.toString());
		
		orderLineWeighted2.removeDiscount(disc);
		
	}
	@Test
	public void testDiscountAmountStyck(){
		
		OrderLine o3s = new OrderLine(product1, 5);
		
		Discount disc = new DiscountAmountMock(date1, date2, 240, false, 4, 1);
		orderLine1.addDiscount(disc);
		
		assertEquals(50, orderLine1.getTotalPrice());
		assertEquals(100, o3s.getTotalPrice());
		
		
		assertEquals("Tandkräm  2st*0.25 kr  0.50 kr", orderLine1.toString());
	}
	
	
	
}
