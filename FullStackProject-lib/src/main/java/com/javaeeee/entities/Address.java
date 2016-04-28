package com.javaeeee.entities;

public class Address {

	public final String street;
	public final String city;
	public final String state;
	public final String zipcode;
	
	
	
	public Address() {
		
		this.street = null;
		this.city = null;
		this.state = null;
		this.zipcode = null;
		
	}
	
	public Address (String street, String city, String state, String zipcode) {
		
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	
	
	public Address (String address) {
	
		String delims = ",";
	
		String[] tokens = address.split(delims);
		
		this.street = tokens[0];
		this.city = tokens[1];
		this.state = tokens[2];
		this.zipcode = tokens[3];
	}
	
	
	public boolean equals(Object address) {
    
    	if (address instanceof Address) {
    		
    		Address add = (Address) address;
    		return this.street == add.street && this.city == add.city && this.state == add.state && this.zipcode == add.zipcode;
    		
    	} else {
    		return false;
    	}
    	
    }
    
    @Override
    public String toString() {
    	
       String street = this.street;
       String city = this.city;
       String state = this.state;
       String zip = this.zipcode;
       
       return street + "," + city + "," + state + "," + zip;
    	
    }


	public String getStreet() {
		return street;
	}


	public String getCity() {
		return city;
	}


	public String getState() {
		return state;
	}


	public String getZipcode() {
		return zipcode;
	}
    
    
    
    
	
}
