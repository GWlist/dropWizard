package com.javaeeee.FullStackProject;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class FullStackProjectConfiguration extends Configuration {
	@JsonProperty
	  private String server;

	public String getServer() {
		return server;
	}

	
	
}
