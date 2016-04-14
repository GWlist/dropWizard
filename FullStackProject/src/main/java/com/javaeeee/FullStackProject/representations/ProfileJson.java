package com.javaeeee.FullStackProject.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaeeee.entities.Item;
import com.javaeeee.entities.Profile;

public class ProfileJson {


	private String userid;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private int rating;
	
	public ProfileJson(Profile p) {
		this.userid = p.userid;
		this.firstName = p.firstName;
		this.lastName = p.lastName;
		this.phoneNumber = p.phoneNumber;
		this.email = p.email;
		this.rating = p.rating;
	}
	
	public ProfileJson() {}
	
	public Profile asProfile() {
		return new Profile(userid, firstName, lastName, rating, phoneNumber, email);
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
	
	
	
}
