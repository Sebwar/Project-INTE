package org.gradle;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.gradle.discounts.Discount;
import org.gradle.discounts.DiscountAmount;

public class OrderLine {
	
	private static ArrayList<Discount> discounts = new ArrayList<Discount>();
	private Product product;
	private int quantity;
	private Money totalPrice;
	
	public OrderLine (Product product, int quantity) {
		if (quantity < 1)
			throw new IllegalArgumentException("Quantity cannot be less than one.");
		this.product = product;
		this.quantity = quantity;
		totalPrice = new Money(product.getCurrency(), product.getPrice() * quantity);
	}
	
	public long getTotalPrice() {
		//Apply discount.
		for (int reg=0; reg < discounts.size(); reg++ )
			if (discounts.get(reg).isDiscounted(this))
				return discounts.get(reg).apply(this);
		return product.getPrice() * quantity;
	}
	
	//ProductID används tills getName()-metod har implementerats i Product.java
	
	public String toString() {
		Money moneyReduction;
		Money moneyEach = new Money(product.getCurrency(), product.getPrice());
		String stringOut;
		if(product.isWeightPriced()) {
			double quantityKg = (double)quantity/1000;
			DecimalFormat decimalFormat = new DecimalFormat("0.###");
			Money moneyKg = new Money(product.getCurrency(), product.getPrice()*1000);
			stringOut = "ProduktID "+product.getID()+"  " + decimalFormat.format(quantityKg)+"kg*"
			+moneyKg.toString()+"/kg  "+totalPrice;
		}
		else {
			stringOut = "ProduktID "+product.getID()+"  "+quantity+"st*"+moneyEach.toString()+"  "+totalPrice;
		}
		
		for (Discount discount : discounts){
			totalPrice = new Money(product.getCurrency(), discount.apply(this));
			/* If discount is an instance of DiscountAmount the isDiscounted method is called from
			 * a DiscountAmount object, otherwise its called from the super class. 
			 */
			if (discount instanceof DiscountAmount && ((DiscountAmount)discount).isDiscounted(this)
					|| !(discount instanceof DiscountAmount) && discount.isDiscounted(this)) {
				moneyReduction = new Money(product.getCurrency(), product.getPrice() * quantity).subtract(totalPrice);
				stringOut += "\n.."+discount.toString()+" -"+moneyReduction.toString();
				break;
			}
		}
		return stringOut;
	}
	
	//Metoder jag är osäker på
	public static void addDiscount(Discount discA){
		discounts.add(discA);
	}
	public static void removeDiscount(Discount discR){
		discounts.remove(discR);
	}
	public int getProductID(){
		return product.getID();
	}
	public long getProductPrice(){
		return product.getPrice();
	}
	public int getProductQuantity(){
		return quantity;
	}
}
