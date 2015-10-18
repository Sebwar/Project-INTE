package org.gradle;

public class Product {
	private Integer id;
	private String name;
	private int price;  //Per gram for weight-priced products.
	private boolean weightPriced;
	
	public Product(int id, String name, int price, boolean weightPriced) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.weightPriced = weightPriced;
	}
	
	public Integer getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public boolean isWeightPriced() {
		return weightPriced;
	}
	
	public String toString() {
		return name;
	}
}
