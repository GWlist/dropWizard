package com.javaeeee.FullStackProject;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaeeee.FullStackProject.representations.ItemJson;
import com.javaeeee.entities.Item;

import io.dropwizard.jackson.Jackson;

public class ItemTest {
	
	
	// Testing our serialization to JSON
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	
	@Test
    public void serializesToJSON() throws Exception {
		Item item = new Item("12", "Test Item", "Washington DC","20",100,1);
        final ItemJson json = new ItemJson(item);
        String jacksonJSON = MAPPER.writeValueAsString(json);
		String jsonFixture = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/item.json"), ItemJson.class));
        assertThat(jacksonJSON).isEqualTo(jsonFixture);

    }
	
	@Test
	public void deserializesFromJSON() throws Exception {
		
		Item item = new Item("12", "Test Item", "Washington DC","20",100,1);
		final ItemJson json = new ItemJson(item);
		assertThat(MAPPER.readValue(fixture("fixtures/item.json"), ItemJson.class))
        .isEqualsToByComparingFields(json);
		
	}
}
