package org.gradle;

public class OrderLineMock extends OrderLine {

	public OrderLineMock() {
		super(new Product(0, "test", 100, false), 10);
	}
	
}
