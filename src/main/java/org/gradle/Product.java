package org.gradle;

public class Product {
	private Integer id;
	private String name;
	private Money price;  //Per gram for weight-priced products.
	private boolean weightPriced;
	
	public Product(int id, String name, Money price, boolean weightPriced) {
		if (id < 0)
			throw new IllegalArgumentException("ID cannot be negative.");
		
		if (price.getTotalAmountInMinorUnit() <= 0)
			throw new IllegalArgumentException();
		
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
	
	public Currency getCurrency() {
		return price.getCurrency();
	}
	
	public long getPrice() {
		return price.getTotalAmountInMinorUnit();
	}
	
	public boolean isWeightPriced() {
		return weightPriced;
	}
	
	public String toString() {
		return name;
	}
}
