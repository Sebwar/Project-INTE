package org.gradle;

import java.util.ArrayList;

public class Category {
	
	ArrayList<Product> products;
	
	public void addProducts(ArrayList<Product> productList) {
		products.addAll(productList);
	}
	
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public void removeProducts(ArrayList<Product> productList) {
		products.removeAll(productList);
	}
	
	public void removeProduct(Product product) {
		products.remove(product);
	}
	
	public boolean inCategory(Product product) {
		return products.contains(product);
	}
}
