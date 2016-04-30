package com.javaeeee.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Profile {

	@Id
	public final String userid;
	public String password;
	public final String firstName;
	public final String lastName;
	public final String phoneNumber;
	public final String email;
	public final Address address;
	public String token;
	public Long lastAccess;
	
	public Profile() {
	    this.userid= null;
		this.password = null;
	    this.firstName = null;
	    this.lastName = null;
	    this.phoneNumber = null;
	    this.email = null;
	    this.address = null;
	  }
	
	public Profile(String userid, String password, String firstName, String lastName, String phoneNumber, String email, Address address) {
	    this.userid= userid;
		this.password = password;
		this.firstName = firstName;
	    this.lastName = lastName;
	    this.phoneNumber = phoneNumber;
	    this.email = email;
	    this.address = address;
	  }

	public String getUserid() {
		return userid;
	}

	public String getPassword() {
		return password;
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

	public Address getAddress() {
		return address;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Long lastAccess) {
		this.lastAccess = lastAccess;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
