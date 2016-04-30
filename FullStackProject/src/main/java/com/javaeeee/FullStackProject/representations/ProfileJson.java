package com.javaeeee.FullStackProject.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaeeee.entities.Address;
import com.javaeeee.entities.Profile;

public class ProfileJson {


	private String userid;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private int rating;
	private Address address;
	public String token;
	public Long lastAccess;
	
	public ProfileJson(Profile p) {
		this.userid = p.userid;
		this.password = p.password;
		this.firstName = p.firstName;
		this.lastName = p.lastName;
		this.phoneNumber = p.phoneNumber;
		this.email = p.email;
		this.rating = p.rating;
		this.address = p.address;
	}
	
	public ProfileJson() {}
	
	public Profile asProfile() {
		return new Profile(userid, password, firstName, lastName, rating, phoneNumber, email, new Address(address.toString()));
	}
	
	
    @JsonProperty
	public String getUserid() {
		return userid;
	}
 
    @JsonProperty
	public String getFirstName() {
		return firstName;
	}
	@JsonProperty
	public String getPassword() {
		return password;
	}
    @JsonProperty
	public String getLastName() {
		return lastName;
	}
    @JsonProperty
	public int getRating() {
		return rating;
	}
    @JsonProperty
	public String getPhoneNumber() {
		return phoneNumber;
	}
    @JsonProperty
	public String getEmail() {
		return email;
	}
    @JsonProperty
    public Address getAddress() {
		return address;
	}
    @JsonIgnore
	public String getToken() {
		return token;
	}
    @JsonIgnore
	public Long getLastAccess() {
		return lastAccess;
	}

	
	
	
}
