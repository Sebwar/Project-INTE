package org.gradle;

import java.util.ArrayList;

public class OrderLine {
	
	private Product product;
	private int quantity;
	private int price;
	private int totalPrice;
	private int productID;
	static ArrayList<DiscountMock> discount = new ArrayList<DiscountMock>();
	
	public OrderLine (Product product, int quantity) {
		this.product = product;		
		this.quantity = quantity;
		price = product.getPrice();
		totalPrice = price * quantity;
		productID = product.getID();
			
	}
	
	public int getTotalPrice() {
		for (int reg=0; reg < discount.size(); reg++ ){
			if (discount.get(reg).isDiscounted(product)){
				return discount.get(reg).apply(this);
			}
			else{
				return totalPrice;
			}
			
			
		}
		return price * quantity;
	}
	
	//ProductID används tills getName()-metod har implementerats i Product.java
	
	public String toString() {
		if(product.isWeightPriced() == true){
			return "ProduktID "+productID+"  "+quantity+"g*"+price+"öre/g  "+totalPrice+"öre";
				
		}
		else {
			return "ProduktID "+productID+"  "+quantity+"st*"+price+"öre  "+totalPrice+"öre";
		}	
	}
	
	//Tillfälliga metoder
	public void addDiscount(DiscountMock discP){
		discount.add(discP);
	}
	public int getProductID(){
		return productID;
	}
	public int getProductPrice(){
		return price;
	}
	public int getProductQuantity(){
		return quantity;
	}
	
		
}
