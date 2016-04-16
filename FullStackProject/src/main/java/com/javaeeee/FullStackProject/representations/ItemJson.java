package com.javaeeee.FullStackProject.representations;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaeeee.entities.Item;

public class ItemJson {

	private String itemid;
	private String name;
	private int price;
	private int numSold;
	private String location;
	private String userid;
	
	
	public ItemJson(Item item) {
		this.itemid = item.itemid;
		this.name = item.name;
		this.price = item.price;
		this.numSold = item.numSold;
		this.location = item.location;
		this.userid = item.userid;
	}
	
	public ItemJson() {}
	
	public Item asItem() {
		return new Item(itemid, name, location, userid, price, numSold);
	}
	
    @JsonProperty
	public String getItemid() {
		return itemid;
	}
    @JsonProperty
	public String getName() {
		return name;
	}
    @JsonProperty
	public int getPrice() {
		return price;
	}
    @JsonProperty
	public int getNumSold() {
		return numSold;
	}
    @JsonProperty
	public String getLocation() {
		return location;
	}
    @JsonProperty
	public String getUserid() {
		return userid;
	}
	
	
	
}
