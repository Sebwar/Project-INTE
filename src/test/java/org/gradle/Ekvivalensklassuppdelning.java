package org.gradle;

import static org.junit.Assert.*;

import java.util.Date;

import org.gradle.discounts.Discount;
import org.junit.Test;

public class Ekvivalensklassuppdelning {
	
	private Product validPrice = new Product(1, "Number 1", new Money(Currency.SEK, 75), false);
	
	
	
	
	Date date1 = new Date(670);
	Date date2 = new Date(875600);
	
	Discount validDiscountPercent = new DiscountPercentMock(date1, date2, 1, false, 35);
	
	
	
	Discount validDiscountAmount = new DiscountAmountMock(date1, date2, 1, false, 4, 1);
	
	
	
	
	@Test
	public void test1() {
		
		OrderLine orderLine = new OrderLine(validPrice, 4);
		assertEquals(300, orderLine.getTotalPrice());
	}
	
	@Test
	public void test2() {
		
		OrderLine orderLine = new OrderLine(validPrice, 4);
		orderLine.addDiscount(validDiscountPercent);
		
		assertEquals(195, orderLine.getTotalPrice());
		orderLine.removeDiscount(validDiscountPercent);
	}
	
	@Test
	public void test3() {
		
		OrderLine orderLine = new OrderLine(validPrice, 4);
		orderLine.addDiscount(validDiscountAmount);
		
		assertEquals(225, orderLine.getTotalPrice());
		orderLine.removeDiscount(validDiscountAmount);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test4() {
		Product priceZero = new Product(2, "Number 2", new Money(Currency.SEK, 0), false);
		
		OrderLine orderLine = new OrderLine(priceZero, 4);
		assertEquals(0, orderLine.getTotalPrice());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test5() {
		Product priceNegative = new Product(3, "Number 3", new Money(Currency.SEK, -50), false);
		
		OrderLine orderLine = new OrderLine(priceNegative, 4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test6() {
		
		OrderLine orderLine = new OrderLine(validPrice, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test7() {
		
		OrderLine orderLine = new OrderLine(validPrice, -4);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void test8() {
		Discount tooBigDiscountPercent = new DiscountPercentMock(date1, date2, 1, false, 125);
		
		OrderLine orderLine = new OrderLine(validPrice, 4);
		orderLine.addDiscount(tooBigDiscountPercent);
	
		orderLine.removeDiscount(tooBigDiscountPercent);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void test9() {
		Discount negativeDiscountPercent = new DiscountPercentMock(date1, date2, 1, false, -25);
		
		OrderLine orderLine = new OrderLine(validPrice, 4);
		orderLine.addDiscount(negativeDiscountPercent);
		
		orderLine.removeDiscount(negativeDiscountPercent);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void test10() {
		Discount zeroDiscountAmount = new DiscountAmountMock(date1, date2, 1, false, 0, 1);
		
		OrderLine orderLine = new OrderLine(validPrice, 4);
		orderLine.addDiscount(zeroDiscountAmount);
		
		orderLine.removeDiscount(zeroDiscountAmount);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void test11() {
		Discount tooBigDiscountAmount = new DiscountAmountMock(date1, date2, 1, false, 2, 4);
		
		OrderLine orderLine = new OrderLine(validPrice, 4);
		orderLine.addDiscount(tooBigDiscountAmount);
		
		orderLine.removeDiscount(tooBigDiscountAmount);
	}
	
	

}
