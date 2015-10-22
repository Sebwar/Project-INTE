package org.gradle;

import java.util.ArrayList;

import org.gradle.discounts.Discount;
import org.gradle.discounts.DiscountAmount;

public class OrderLine {
	
	private Product product;
	private int quantity;
	private double quantityKg;
	private Money totalPrice;
	private int productID;
	private String stringOut;
	private Money moneySt;
	static ArrayList<Discount> discounts = new ArrayList<Discount>();
	
	public OrderLine (Product product, int quantity) {
		this.product = product;		
		this.quantity = quantity;
		totalPrice = new Money(product.getCurrency(), product.getPrice() * quantity);
		productID = product.getID();
		quantityKg = (double)quantity/1000;
		
		moneySt = new Money(product.getCurrency(), product.getPrice());
	}
	
	public long getTotalPrice() {
		//Apply discount.
		for (int reg=0; reg < discounts.size(); reg++ )
			if (discounts.get(reg).isDiscounted(this, false))
				return discounts.get(reg).apply(this);
		return product.getPrice() * quantity;
	}
	
	//ProductID används tills getName()-metod har implementerats i Product.java
	
	public String toString() {
		Money moneyReduction = new Money(totalPrice.getCurrency(), totalPrice.getTotalAmountInMinorUnit());
		if(product.isWeightPriced()) {
			Money moneyKg = new Money(product.getCurrency(), product.getPrice()*1000);
			stringOut = "ProduktID "+productID+"  "+quantityKg+"kg*"+moneyKg.toString()+"/kg  "+totalPrice;
			for (Discount discount : discounts){
				totalPrice = new Money(product.getCurrency(), discount.apply(this));
				if (discount instanceof DiscountAmount && ((DiscountAmount)discount).isDiscounted(this, false) || !(discount instanceof DiscountAmount) && discount.isDiscounted(this, false)) {
					moneyReduction = new Money(product.getCurrency(), product.getPrice() * quantity).subtract(totalPrice);
					return stringOut+"\n.."+discount.toString()+" -"+moneyReduction.toString();
				}
				
			}
			return stringOut;
				
		}
		else {
			stringOut = "ProduktID "+productID+"  "+quantity+"st*"+moneySt.toString()+"  "+totalPrice;
			for (Discount discount : discounts){
				totalPrice = new Money(product.getCurrency(), discount.apply(this));
				if (discount instanceof DiscountAmount && ((DiscountAmount)discount).isDiscounted(this, false) || !(discount instanceof DiscountAmount) && discount.isDiscounted(this, false)) {
					moneyReduction = new Money(product.getCurrency(), product.getPrice() * quantity).subtract(totalPrice);
					return stringOut+"\n.."+discount.toString()+" -"+moneyReduction.toString();
				}
				
			}
			return stringOut;
		}	
	}
	
	//Metoder jag är osäker på
	public void addDiscount(Discount discA){
		discounts.add(discA);
	}
	public void removeDiscount(Discount discR){
		discounts.remove(discR);
	}
	public int getProductID(){
		return productID;
	}
	public long getProductPrice(){
		return product.getPrice();
	}
	public int getProductQuantity(){
		return quantity;
	}
}
