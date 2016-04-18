package com.javaeeee.FullStackProject;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;


import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaeeee.FullStackProject.representations.ProfileJson;
import com.javaeeee.entities.Address;
import com.javaeeee.entities.Profile;

import io.dropwizard.jackson.Jackson;

public class ProfileTest {
	
	
	// Testing our serialization to JSON
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	
	@Test
    public void serializesToJSON() throws Exception {
		
		Profile profile = new Profile("25", "Mike", "Rothkopf",10,"555-555-5555","test@test.com", new Address("14 Elm St","Washington", "DC", "20001"));
        final ProfileJson json = new ProfileJson(profile);
        String jacksonJSON = MAPPER.writeValueAsString(json);
        String jsonFixture = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/profile.json"), ProfileJson.class));
        assertThat(jacksonJSON).isEqualTo(jsonFixture);

    
    }
	
	// TODO: This test won't work with Address class included, need to customize
	//@Test
	public void deserializesFromJSON() throws Exception {
		
		Profile profile = new Profile("25", "Mike", "Rothkopf",10,"555-555-5555","test@test.com", new Address("14 Elm St","Washington", "DC", "20001"));
		final ProfileJson json = new ProfileJson(profile);
		assertThat(MAPPER.readValue(fixture("fixtures/profile.json"), ProfileJson.class))
        .isEqualsToByComparingFields(json);
		
	}
}
