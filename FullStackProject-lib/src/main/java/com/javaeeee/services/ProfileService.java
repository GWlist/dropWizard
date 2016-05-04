package com.javaeeee.services;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.mongodb.morphia.Datastore;
import org.w3c.dom.Document;

import com.javaeeee.entities.Item;
import com.javaeeee.entities.Profile;

public class ProfileService {

	
	public static Profile getTopUser(Datastore ds) {
		
		Map<Profile, Integer> profileRating = new HashMap<Profile, Integer>();
		
		List<Profile> profiles = ds.find(Profile.class).asList();
		
		
		for (int i = 0;i<profiles.size();i++) {
			
			List<Item> profileItems = ds.find(Item.class, "userid =", profiles.get(i).getUserid()).asList();
			
			
			int ratings = 0;
			int totalSold = 0;
			
			for (int a = 0;a<profileItems.size();a++) {
				
				if (profileItems.get(a).getNumSold() == 0) {
				  totalSold = totalSold + 1;
				  ratings = ratings + profileItems.get(a).getRating();
				}
			}
			
			// Don't use sellers that have sold 0 or only 1 item
			if (totalSold > 1) {
			  profileRating.put(profiles.get(i), ratings/totalSold);
			}
			else {
			  profileRating.put(profiles.get(i), 0);
			}
			
			
			
		}
		
		int index = 0;
		int currentHigh = 0;
		
		for (int p=0;p<profiles.size();p++) {
		   
			if (profileRating.get(profiles.get(p)) > currentHigh) {
			  index = p;
			  currentHigh = profileRating.get(profiles.get(p));
			}
			
		}
		
		return profiles.get(index);
		
		
		
	
		
	}
	
	// Distance method used to find the distance between a user and an item.  The method will take
	// zip codes as input and call a google maps api to determine the longitude & latitude of each. 
	// Then a distance calculation is called which will determine the total distance in miles.
	
    public static double distance(String zip1, String zip2) throws Exception {
		
		String[] latlong1 = getLatLongPositions(zip1);
		String[] latlong2 = getLatLongPositions(zip2);
		
		double lat1 = Double.parseDouble(latlong1[0]);
		double long1 = Double.parseDouble(latlong1[1]);
		double lat2 = Double.parseDouble(latlong2[0]);
		double long2 = Double.parseDouble(latlong2[1]);
		
		double miles = distance(lat1,long1,lat2,long2);
		
		return miles;
    }
	
    /***************************************************************************************
	*    Title: GeoDataSource -- Sample Codes (Java)
	*    Author: GeoDataSource
	*    Date: Unknown
	*    Code version: 1.0
	*    Availability: https://www.geodatasource.com/developers/java
	*
	***************************************************************************************/
	
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		
		return dist;
	  
	}
	
	
	/***************************************************************************************
	*    Title: Java program to get Latitude and Longitude
	*    Author: Santhosh Reddy Mandadi
	*    Date: 09-Aug-2013
	*    Code version: 1.0
	*    Availability: www.santhoshreddymandadi.com/java/java-program-to-get-latitude-longitude-points.html
	*
	***************************************************************************************/
		public static String[] getLatLongPositions(String zipcode) throws Exception
		  {
		    int responseCode = 0;
		    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(zipcode, "UTF-8") + "&sensor=true";
		    URL url = new URL(api);
		    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
		    httpConnection.connect();
		    responseCode = httpConnection.getResponseCode();
		    if(responseCode == 200)
		    {
		      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
		      Document document = builder.parse(httpConnection.getInputStream());
		      XPathFactory xPathfactory = XPathFactory.newInstance();
		      XPath xpath = xPathfactory.newXPath();
		      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
		      String status = (String)expr.evaluate(document, XPathConstants.STRING);
		      if(status.equals("OK"))
		      {
		         expr = xpath.compile("//geometry/location/lat");
		         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
		         expr = xpath.compile("//geometry/location/lng");
		         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
		         return new String[] {latitude, longitude};
		      }
		      else
		      {
		         throw new Exception("Error from the API - response status: "+status);
		      }
		    }
		    return null;
		  }
		


	    private static double deg2rad(double deg) {
		  return (deg * Math.PI / 180.0);
	    }

	    private static double rad2deg(double rad) {
		  return (rad * 180 / Math.PI);
	    }
	
}
