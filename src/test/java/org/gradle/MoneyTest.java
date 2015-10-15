package org.gradle;

import org.junit.Test;

public class MoneyTest {
	private Money money;
	
	@Test
	public void testConstructor() {
		money = new Money(Currency.USD, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithNullCurrency() {
		money = new Money(null, 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithNegativeAmount() {
		money = new Money(Currency.EUR, -1);
	}
}
