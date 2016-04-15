package com.javaeeee.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Profile {

	@Id
	public final String userid;
	public final String firstName;
	public final String lastName;
	public final String phoneNumber;
	public final String email;
	public final int rating;
	
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public int getRating() {
		return rating;
	}
	
	
}
