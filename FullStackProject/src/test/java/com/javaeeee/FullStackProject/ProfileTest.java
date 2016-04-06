package com.javaeeee.FullStackProject;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaeeee.FullStackProject.representations.Profile;

import io.dropwizard.jackson.Jackson;

public class ProfileTest {
	
	
	// Testing our serialization to JSON
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	
	@Test
    public void serializesToJSON() throws Exception {
        final Profile profile = new Profile("25", "Mike", "Rothkopf",10,"555-555-5555","test@test.com");
        String jacksonJSON = MAPPER.writeValueAsString(profile);
		String jsonFixture = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/profile.json"), Profile.class));
        assertThat(jacksonJSON).isEqualTo(jsonFixture);

    }
	
	@Test
	public void deserializesFromJSON() throws Exception {
		
		final Profile profile = new Profile("25", "Mike", "Rothkopf",10,"555-555-5555","test@test.com");
		assertThat(MAPPER.readValue(fixture("fixtures/profile.json"), Profile.class))
        .isEqualsToByComparingFields(profile);
		
	}
}
