package org.gradle;

public class OrderLine {
	
	private Product product;
	private int quantity;
	private int price;
	
	public OrderLine (Product product, int quantity) {
		this.product = product;		
		this.quantity = quantity;
		price = product.getPrice();
			
	}
	
	public int getTotalPrice() {
		return price * quantity;
	}
	
	public String toString() {
		return null;
	}
}
