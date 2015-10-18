package org.gradle;

public class OrderLine {
	
	private Product product;
	private int quantity;
	private int price;
	private int totalPrice;
	private int productID;
	
	public OrderLine (Product product, int quantity) {
		this.product = product;		
		this.quantity = quantity;
		price = product.getPrice();
		totalPrice = price * quantity;
		productID = product.getID();
			
	}
	
	public int getTotalPrice() {
		return price * quantity;
	}
	
	//ProductID anv�nds tills getName()-metod har implementerats i Product.java
	
	public String toString() {
		if(product.isWeightPriced() == true){
			return "ProduktID "+productID+"  "+quantity+"g*"+price+"�re/g  "+totalPrice+"�re";
				
		}
		else {
			return "ProduktID "+productID+"  "+quantity+"st*"+price+"�re  "+totalPrice+"�re";
		}
			
		
	}
}
