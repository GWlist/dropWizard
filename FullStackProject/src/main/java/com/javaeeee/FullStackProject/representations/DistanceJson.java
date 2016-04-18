package com.javaeeee.FullStackProject.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DistanceJson {

	private String profile1;
    private String profile2;
    private String distance;

    public DistanceJson() {}

    public DistanceJson(String p1, String p2, String distance) {
        this.profile1 = p1;
        this.profile2 = p2;
        this.distance = distance;
    }

	
    @JsonProperty
    public String getProfile1() {
		return profile1;
	}

	
    @JsonProperty
	public String getProfile2() {
		return profile2;
	}

	
    @JsonProperty
	public String getDistance() {
		return distance;
	}

	
    
	
}
