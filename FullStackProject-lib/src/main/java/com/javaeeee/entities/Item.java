package com.javaeeee.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Item {

	@Id
	public final String itemid;
	public final String name;
	public final int price;
	public final int numSold;
	public final String location;
	public final String userid;
	
	
	public Item() {
	    this.itemid= null;
	    this.name = null;
	    this.location = null;
	    this.userid = null;
	    this.price = 0;
	    this.numSold = 0;
	  }
	
	public Item(String itemid, String name, String location,String userid,int price, int numSold) {
	    this.itemid= itemid;
	    this.name = name;
	    this.location = location;
	    this.userid = userid;
	    this.price = price;
	    this.numSold = numSold;
	  }

	public String getItemid() {
		return itemid;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getNumSold() {
		return numSold;
	}

	public String getLocation() {
		return location;
	}

	public String getUserid() {
		return userid;
	}
	
	
	
}
