package org.gradle;

public class ReceiptMockClass {
	
	
	private int id;
	private String orgNr;
	private String name;
	private String firstName;
	private String lastName;
	private String address;
	private String street;
	private String phone;
	private int amount;
	private boolean priceByWeight;
	private int productPrice;
	private int discount;
	private int totalCost;	
	
	//Mocks Product
	public ReceiptMockClass (int id, String name, int amount, boolean priceByWeight, int productPrice) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.priceByWeight = priceByWeight; 
		this.productPrice = productPrice;
		this.totalCost += (productPrice*amount);
	}
	
	//Mocks Customer
	public ReceiptMockClass (String orgNr, String firstName, String lastName, String address, String street, String phone) {
		this.orgNr = orgNr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.address = address;
        this.phone = phone;
	}
	
	public int getId() {
		return id;
	}
	
	public String getOrgNr() {
		return orgNr;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public boolean getPriceByWeight() {
		return priceByWeight;
	}
	
	public int getProductPrice() {
		return productPrice;
	}
	
	public int getDiscount() {
		return discount;
	}

	public int getTotalCost() {
		return totalCost;
	}
	
}