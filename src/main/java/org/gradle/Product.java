package org.gradle;

public class Product {
	private int id;
	private String name;
	private int price;
	private boolean weightPriced;
	
	public Product(int id, String name, int price, boolean weightPriced) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.weightPriced = weightPriced;
	}
	
	public int getPrice() {
		return price;
	}
	
	public boolean isWeightPriced() {
		return weightPriced;
	}
}
