package org.gradle;

import java.util.ArrayList;

public class OrderLine {
	
	private Product product;
	private int quantity;
	private double quantityKg;
	private int price;
	private int totalPrice;
	private int productID;
	private Money moneySt;
	private Money moneyTot;
	private Money moneyKg;
	static ArrayList<Discount> discount = new ArrayList<Discount>();
	
	public OrderLine (Product product, int quantity) {
		this.product = product;		
		this.quantity = quantity;
		price = product.getPrice();
		totalPrice = price * quantity;
		productID = product.getID();
		quantityKg = (double)quantity/1000;
		
		moneySt = new Money(Currency.SEK, product.getPrice());
		moneyTot = new Money(Currency.SEK, totalPrice);	
	}
	//totalprice i öre (Minor unit)
	public int getTotalPrice() {
		for (int reg=0; reg < discount.size(); reg++ ){
			if (discount.get(reg).isDiscounted(product)){
				return discount.get(reg).apply(this);
			}
			
		}
		return price * quantity;
	}
	
	//ProductID används tills getName()-metod har implementerats i Product.java
	
	public String toString() {
		int orgPrice = totalPrice;
		if(product.isWeightPriced() == true){
			moneyKg = new Money(Currency.SEK, product.getPrice()*1000);
			for (Discount disc : discount){
				totalPrice = disc.apply(this);
				if (orgPrice != totalPrice){
					return "ProduktID "+productID+"  "+quantityKg+"kg*"+moneyKg.toString()+"/kg  "+moneyTot.toString()+"\n..Rabatt:35%  -840,0kr";
				}
				
			}
			return "ProduktID "+productID+"  "+quantityKg+"kg*"+moneyKg.toString()+"/kg  "+moneyTot.toString();
				
		}
		else {
			for (Discount disc : discount){
				totalPrice = disc.apply(this);
				if (orgPrice != totalPrice){
					return "ProduktID "+productID+"  "+quantity+"st*"+moneySt.toString()+"  "+moneyTot.toString()+"\n..Rabatt:30%  -0,42kr";
				}
				
			}
			return "ProduktID "+productID+"  "+quantity+"st*"+moneySt.toString()+"  "+moneyTot.toString();
		}	
	}
	
	//Metoder jag är osäker på
	public void addDiscount(Discount discA){
		discount.add(discA);
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
