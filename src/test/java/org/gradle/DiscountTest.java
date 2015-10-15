package org.gradle;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DiscountTest {
	
	private static final Date VALID_START_TIME = new Date();
	private static final Date VALID_END_TIME = new Date();
	private static final int VALID_PRODUCT_ID = 0;
	private static final int VALID_DISCOUNT_PRODUCT_ID = 1;
	
	private static final int VALID_PERCENT = 50; //0-100
	private static final int VALID_REQ_AMOUNT = 3; // < 0
	private static final int VALID_RED_AMOUNT = 1; // < 0 && > reqAmount
	
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	//Discount IllegalArgument
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeProduct() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, -1, VALID_PERCENT);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidDate() {
		Discount discount = new DiscountPercent(new Date(1), new Date(0), VALID_PRODUCT_ID, VALID_PERCENT);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddNegativeProduct() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, VALID_PERCENT);
		discount.addProduct(-1);
	}
	//DiscountPercent IllegalArgument
	@Test(expected=IllegalArgumentException.class)
	public void testPercentUnderMin() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPercentOverMax() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, 101);
	}
	
	//DiscountAmount IllegalArgument
	@Test(expected=IllegalArgumentException.class)
	public void testReqLowerThanRed() {
		Discount discount = new DiscountAmount(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, 0, VALID_RED_AMOUNT);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeReqAmount() {
		Discount discount = new DiscountAmount(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, -1, VALID_RED_AMOUNT);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeRedAmount() {
		Discount discount = new DiscountAmount(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, VALID_REQ_AMOUNT, -1);
	}
	
	//Method tests
	@Test
	public void testPercent() { //No discount
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, VALID_PERCENT);
		int price = discount.apply(new OrderLineMock());
		assertEquals(10, price);
	}
	
	@Test
	public void testReducedPricePercent() { //Discount
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_DISCOUNT_PRODUCT_ID, VALID_PERCENT);
		int price = discount.apply(new OrderLineMock());
		assertEquals(5, price); //price * percent / 100 = 10 * 50 / 100 = 5
	}
	
	@Test
	public void testAmount() { //No discount
		Discount discount = new DiscountAmount(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, VALID_REQ_AMOUNT, VALID_RED_AMOUNT);
		int price = discount.apply(new OrderLineMock());
		assertEquals(30, price); //productAmount = 3, price = 10
	}
	
	@Test
	public void testReducedPriceAmount() { //Discount
		Discount discount = new DiscountAmount(VALID_START_TIME, VALID_END_TIME, VALID_DISCOUNT_PRODUCT_ID, VALID_REQ_AMOUNT, VALID_RED_AMOUNT);
		int price = discount.apply(new OrderLineMock());
		assertEquals(20, price); //productAmount = 3, price = 10, reqAmount = 3, redAmount = 1 ; (3-1) * price = 20
	}
}
