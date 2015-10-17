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
}
