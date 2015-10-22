package org.gradle;

public class OrderLineMock extends OrderLine {

	public OrderLineMock(int productID) {
		super(new Product(productID, "test", new Money(Currency.SEK, 10), false), 3);
	}
	public int getProductQuantity() {
		return 3;
	}
}
