package org.gradle;

public class OrderLine {
	
	Product product;	
	Double amount;
	int price;
	int totalPrice;
	
	public OrderLine (Product inProduct, double inAmount){
		
		product = inProduct;		
		amount = inAmount;
		price = product.getPrice();
			
	}
	
	public int getTotalPrice(){
		
		if(product.isWeightPriced() == true){
			
			Double totPrice = Math.floor(price * amount);
			int tP = totPrice.intValue();
			
			totalPrice = tP;
			
			return totalPrice;
			
			
		}
		else {
			int tP = amount.intValue();
			totalPrice = price * tP;
			
			return totalPrice;
		}
		
		
		
	}
	

}
