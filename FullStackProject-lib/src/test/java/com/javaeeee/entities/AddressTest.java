package com.javaeeee.entities;


import org.junit.Test;
import org.junit.Assert;

public class AddressTest {

	@Test
	public void toStringTest() throws Exception {
	
		Address add1 = new Address("14 E St","Washington","DC","20001");
		Assert.assertEquals(add1.toString(), "14 E St,Washington,DC,20001");
	}
	
	@Test
	public void deserialization() throws Exception {
		
		String address = "14 E St,Washington,DC,20001";
		Address add = new Address(address);
		
		Assert.assertEquals(add.street, "14 E St");
		Assert.assertEquals(add.city, "Washington");
		Assert.assertEquals(add.state, "DC");
		Assert.assertEquals(add.zipcode, "20001");
		
		
	}
	
	//@Test
	public void distanceTest() throws Exception {
		
		Address add1 = new Address("14 E St","Washington","DC","20001");
		Address add2 = new Address("10 D St", "McLean", "VA", "22102");
		String latLongs[] = Address.getLatLongPositions(add1.getZipcode());
		String latLongs2[] = Address.getLatLongPositions(add2.getZipcode());
	
		Double distance1 = Address.distance(Double.parseDouble(latLongs[0]), Double.parseDouble(latLongs[1]), Double.parseDouble(latLongs2[0]), Double.parseDouble(latLongs2[1]));
		
		Double distance2 = Address.distance(add1.getZipcode(), add2.getZipcode());
		
		Assert.assertEquals(latLongs[0], "38.9120680");
		Assert.assertEquals(latLongs[1], "-77.0190228");
		Assert.assertEquals(distance1, distance2);
		 
		
		
	    
	}
	
	
}
