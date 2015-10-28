package org.gradle;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {

	private static HashMap<Integer, Category> categories = new HashMap<Integer, Category>();
	private ArrayList<Integer> productIDs = new ArrayList<Integer>();
	private String name;
	private int categoryID;
	
	public Category(int categoryID, String name) {
		if (categoryID < 0)
			throw new IllegalArgumentException("CategoryID cannot be negative!");
		
		if (categories.containsKey(categoryID))
			throw new IllegalArgumentException("Given CategoryID already exists!");
		
		if (name == null)
			throw new IllegalArgumentException("Name cannot be null!");
		
		if (name.isEmpty())
			throw new IllegalArgumentException("Category name cannot be empty!");
		
		this.categoryID = categoryID;
		this.name = name;
	}
	
	public static Category getCategoryByID(int id) {
		return categories.get(id);
	}
	
	public static void clearList() { //For testing
		categories.clear();
	}
	
	public void addToList() {
		if (!categories.containsValue(this))
			categories.put(categoryID, this);
	}
	
	public void addProducts(ArrayList<Integer> productList) {
		productIDs.addAll(productList);
	}
	
	public void addProduct(int product) {
		productIDs.add(product);
	}
	
	public void removeProducts(ArrayList<Integer> productList) {
		productIDs.removeAll(productList);
	}
	
	public void removeProduct(int product) {
		productIDs.remove(product);
	}
	
	public boolean inCategory(int productID) {
		return productIDs.contains(productID);
	}
	
	public int getID() {
		return categoryID;
	}
	
	public String getName() {
		return name;
	}
}
