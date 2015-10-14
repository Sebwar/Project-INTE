package org.gradle;

public class Customer {
	
	private String firstName;
	private String lastName;
	private String street;
	private String address;
	
	//Construct
	public Customer (String firstName, String lastName, String address, String street)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.address = address;
    }
	
	public String getFullName() {
	    return firstName + " " + lastName;
	}
	
	public String getAdress() {
	    return street + " " + address;
	}
	
	@Override		
	public String toString()
    {
        //Create a String that represents this object
        return firstName + " " + lastName + "\n" + street + "\n" + address;
    }
	
	
}

