package org.gradle;

public class Customer {
	
	private String orgNr;
	private String firstName;
	private String lastName;
	private String street;
	private String address;
	private String phone;
	
	//Construct
	public Customer (String orgNr, String firstName, String lastName, String address, String street, String phone)
    {
		this.firstName = orgNr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.address = address;
        this.address = phone;
    }
	
	public String getFullName() {
	    return firstName + " " + lastName;
	}
	

	@Override		
	public String toString()
    {
        //Create a String that represents this object
        return orgNr + "\n" + firstName + " " + lastName + "\n" + street + "\n" + address + "\n" + phone;
    }
	
	
}

