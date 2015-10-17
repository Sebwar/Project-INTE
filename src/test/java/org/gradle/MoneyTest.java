package org.gradle;

import static org.junit.Assert.*;
import org.junit.Test;

public class MoneyTest {
	private Money money;
	
	@Test
	public void testConstructor() {
		money = new Money(Currency.USD, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorNullCurrency() {
		money = new Money(null, 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorNegativeAmount() {
		money = new Money(Currency.EUR, -1);
	}
	
	@Test
	public void testConvenienceConstructor() {
		money = new Money(Currency.SEK, 47, 30);
		assertEquals(4730, money.getTotalAmountInMinorUnit());
	}
	
	@Test
	public void testAdd() {
		money = new Money(Currency.SEK, 55);
		money = money.add(1);
		assertEquals(56, money.getTotalAmountInMinorUnit());
	}
	
	@Test
	public void testAddZero() {
		money = new Money(Currency.EUR, 41);
		money = money.add(0);
		assertEquals(41, money.getTotalAmountInMinorUnit());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddNegative() {
		money = new Money(Currency.SEK, 88);
		money = money.add(-1);
	}
	
	@Test
	public void testAddMoneyObject() {
		money = new Money(Currency.USD, 76);
		money = money.add(new Money(Currency.USD, 52));
		assertEquals(128, money.getTotalAmountInMinorUnit());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddMoneyObjectDifferentCurrency() {
		money = new Money(Currency.USD, 72);
		money = money.add(new Money(Currency.SEK, 53));
	}
	
	@Test
	public void testSubtract() {
		money = new Money(Currency.SEK, 55);
		money = money.subtract(1);
		assertEquals(54, money.getTotalAmountInMinorUnit());
	}
	
	@Test
	public void testSubtractZero() {
		money = new Money(Currency.EUR, 41);
		money = money.subtract(0);
		assertEquals(41, money.getTotalAmountInMinorUnit());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSubtractNegative() {
		money = new Money(Currency.SEK, 88);
		money = money.subtract(-1);
	}
	
	@Test
	public void testSubtractMoneyObject() {
		money = new Money(Currency.USD, 76);
		money = money.subtract(new Money(Currency.USD, 52));
		assertEquals(24, money.getTotalAmountInMinorUnit());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSubtractMoneyObjectDifferentCurrency() {
		money = new Money(Currency.USD, 72);
		money = money.subtract(new Money(Currency.SEK, 53));
	}
	
	@Test
	public void testCompareToLessThan() {
		money = new Money(Currency.EUR, 111);
		Money other = new Money(Currency.EUR, 110);
		assertTrue(money.compareTo(other) > 0);
	}
	
	@Test
	public void testCompareToGreaterThan() {
		money = new Money(Currency.EUR, 56);
		Money other = new Money(Currency.EUR, 57);
		assertTrue(money.compareTo(other) < 0);
	}
	
	@Test
	public void testCompareToEqualTo() {
		money = new Money(Currency.EUR, 71);
		Money other = new Money(Currency.EUR, 71);
		assertTrue(money.compareTo(other) == 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCompareToNull() {
		money = new Money(Currency.EUR, 111);
		Money other = null;
		money.compareTo(other);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCompareDifferentCurrencies() {
		money = new Money(Currency.EUR, 110);
		Money other = new Money(Currency.USD, 110);
		assertTrue(money.compareTo(other) == 0);
	}
	
	@Test
	public void testToString() {
		money = new Money(Currency.SEK, 127);
		assertEquals("1.27 kr", money.toString());
	}
	
	@Test
	public void testToStringPrefixedSign() {
		money = new Money(Currency.USD, 127);
		assertEquals("$ 1.27", money.toString());
	}
}
