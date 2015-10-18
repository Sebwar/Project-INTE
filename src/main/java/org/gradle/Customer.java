package org.gradle;

public class Customer {
	private String orgNr;
	private String firstName;
	private String lastName;
	private String street;
	private String address;
	private String phone;
	
	//Construct
	public Customer (String orgNr, String firstName, String lastName, String address, String street, String phone) {
		this.orgNr = orgNr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.address = address;
        this.phone = phone;
    }
	
	public String getOrgnr() {
	    return orgNr;
	}
	
	public String getFullName() {
	    return firstName + " " + lastName;
	}
	
	public String getStreet() {
	    return street;
	}
	
	public String getAddress() {
	    return address;
	}
	
	public String getPhone() {
	    return phone;
	}
	
	@Override
	//Formated String that represents this object
	public String toString() {
        return orgNr + "\n" + firstName + " " + lastName + "\n" + street + "\n" + address + "\n" + phone;
    }	
}

