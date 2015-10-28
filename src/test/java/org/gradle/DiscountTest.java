package org.gradle;

import static org.junit.Assert.*;

import java.util.Date;

import org.gradle.discounts.Discount;
import org.gradle.discounts.DiscountAmount;
import org.gradle.discounts.DiscountPercent;
import org.junit.Test;

public class DiscountTest {
	
	private static final Date VALID_START_TIME = new Date();
	private static final Date VALID_END_TIME = new Date();
	private static final int VALID_PRODUCT_ID = 0;
	private static final int VALID_CATEGORY_ID = 0;
	private static final int VALID_DISCOUNT_PRODUCT_ID = 1;
	private static final int VALID_DISCOUNT_CATEGORY_ID = 2;
	
	private static final int VALID_PERCENT = 50; //0-100
	private static final int VALID_REQ_AMOUNT = 3; // < 0
	private static final int VALID_RED_AMOUNT = 1; // < 0 && > reqAmount
	
	private static final Money VALID_MONEY = new Money(Currency.SEK, 10);
	
	private static final Discount PERCENT_DISCOUNT = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_DISCOUNT_PRODUCT_ID, false,  VALID_PERCENT);
	private static final Discount AMOUNT_DISCOUNT = new DiscountAmount(VALID_START_TIME, VALID_END_TIME, VALID_DISCOUNT_PRODUCT_ID, false,  VALID_REQ_AMOUNT, VALID_RED_AMOUNT);
	private static final Discount AMOUNT_CATEGORY_DISCOUNT = new DiscountAmount(VALID_START_TIME, VALID_END_TIME, VALID_DISCOUNT_CATEGORY_ID, true,  VALID_REQ_AMOUNT, VALID_RED_AMOUNT);
	private static final Category DISCOUNTED_CATEGORY = new Category(VALID_DISCOUNT_CATEGORY_ID, "Test");
	
	//Discount IllegalArgument
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeProduct() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, -1, false, VALID_PERCENT);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidDate() {
		Discount discount = new DiscountPercent(new Date(1), new Date(0), VALID_PRODUCT_ID, false, VALID_PERCENT);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddNegativeProduct() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, false,  VALID_PERCENT);
		discount.addItem(-1);
	}
	//DiscountPercent IllegalArgument
	@Test(expected=IllegalArgumentException.class)
	public void testPercentUnderMin() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, false,  -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPercentOverMax() {
		Discount discount = new DiscountPercent(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, false,  101);
	}
	
	//DiscountAmount IllegalArgument
	@Test(expected=IllegalArgumentException.class)
	public void testReqLowerThanRed() {
		Discount discount = new DiscountAmount(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, false,  0, VALID_RED_AMOUNT);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeReqAmount() {
		Discount discount = new DiscountAmount(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, false,  -1, VALID_RED_AMOUNT);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeRedAmount() {
		Discount discount = new DiscountAmount(VALID_START_TIME, VALID_END_TIME, VALID_PRODUCT_ID, false,  VALID_REQ_AMOUNT, -1);
	}
	
	//Method tests
	@Test
	public void testPercent() { //No discount
		long price = PERCENT_DISCOUNT.apply(new OrderLineMock(VALID_PRODUCT_ID));
		assertEquals(30, price);
	}
	
	@Test
	public void testReducedPricePercent() { //Discount
		long price = PERCENT_DISCOUNT.apply(new OrderLineMock(VALID_DISCOUNT_PRODUCT_ID));
		assertEquals(15, price);
	}
	
	@Test
	public void testAmount() { //No discount
		long price = AMOUNT_DISCOUNT.apply(new OrderLineMock(VALID_PRODUCT_ID));
		assertEquals(30, price);
	}
	
	@Test
	public void testReducedPriceAmount() { //Discount
		long price = AMOUNT_DISCOUNT.apply(new OrderLineMock(VALID_DISCOUNT_PRODUCT_ID));
		assertEquals(20, price);
	}
	
	@Test
	public void testProductIsNotDiscounted() {
		assertEquals(false, AMOUNT_DISCOUNT.isDiscounted(new OrderLine(new Product(VALID_PRODUCT_ID, "Test", VALID_MONEY, false), 3)));
	}
	
	@Test
	public void testProductIsDiscounted() {
		assertEquals(true, AMOUNT_DISCOUNT.isDiscounted(new OrderLine(new Product(VALID_DISCOUNT_PRODUCT_ID, "Test", VALID_MONEY, false), 3)));
	}
	@Test
	public void testCategoryIsNotDiscounted() {
		DISCOUNTED_CATEGORY.addProduct(VALID_DISCOUNT_PRODUCT_ID);
		DISCOUNTED_CATEGORY.addToList();
		assertEquals(false, AMOUNT_CATEGORY_DISCOUNT.isDiscounted(new OrderLine(new Product(VALID_PRODUCT_ID, "Test", VALID_MONEY, false), 3)));
	}
	
	@Test
	public void testCategoryIsDiscounted() {
		DISCOUNTED_CATEGORY.addProduct(VALID_DISCOUNT_PRODUCT_ID);
		DISCOUNTED_CATEGORY.addToList();
		assertEquals(true, AMOUNT_CATEGORY_DISCOUNT.isDiscounted(new OrderLine(new Product(VALID_DISCOUNT_PRODUCT_ID, "Test", VALID_MONEY, false), 3)));
	}
}
