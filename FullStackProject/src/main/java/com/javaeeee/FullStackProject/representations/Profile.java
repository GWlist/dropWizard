package com.javaeeee.FullStackProject.representations;

public class Profile {

	private final String userid;
	private final String firstName;
	private final String lastName;
	private final int rating;
	
	public Profile() {
	    this.userid= null;
	    this.firstName = null;
	    this.lastName = null;
	    this.rating = 0;
	  }
	
	public Profile(String userid, String firstName, String lastName,int rating) {
	    this.userid= userid;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.rating = rating;
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
	
	
}
