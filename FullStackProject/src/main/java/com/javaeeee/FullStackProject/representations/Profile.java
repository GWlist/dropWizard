package com.javaeeee.FullStackProject.representations;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Profile {

	@Id
	private final String userid;
	private final String firstName;
	private final String lastName;
	private final String phoneNumber;
	private final String email;
	private final int rating;
	
	public Profile() {
	    this.userid= null;
	    this.firstName = null;
	    this.lastName = null;
	    this.rating = 0;
	    this.phoneNumber = null;
	    this.email = null;
	  }
	
	public Profile(String userid, String firstName, String lastName,int rating, String phoneNumber, String email) {
	    this.userid= userid;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.rating = rating;
	    this.phoneNumber = phoneNumber;
	    this.email = email;
	  }

	public String getUserid() {
		return userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getRating() {
		return rating;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	
	
	
}
