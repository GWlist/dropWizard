package com.javaeeee.FullStackProject;

import static org.fest.assertions.api.Assertions.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.javaeeee.FullStackProject.representations.ItemJson;
import com.javaeeee.entities.Item;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;


// Test resources in web app
public class ApplicationTest {

	private Client client;
	private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test.yml");
	//final Profile profile = new Profile("25", "Mike", "Rothkopf",10,"555-555-5555","test@test.com");
	
	private ItemJson item = new ItemJson(new Item("12", "Test Item", "Washington DC","20",100,1));
	
	@ClassRule
    public static final DropwizardAppRule<FullStackProjectConfiguration> RULE =
            new DropwizardAppRule<FullStackProjectConfiguration>(FullStackProjectApplication.class, CONFIG_PATH);
	
	
	@Before
    public void setUp() {
      client = ClientBuilder.newClient();
    }
	
	
	//@Test
    public void createAndRetrieveItem() {
      
		// First test that the post is working
		// This will also test our database connection setup
        
		Response response = client.target(String.format("http://localhost:" + RULE.getLocalPort() + "/service/items/create"))
				.request()
				.post(Entity.entity(item, MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatus()).isEqualTo(201);
		
		
		//  Confirm test saved correctly by retrieving the item
		final ItemJson testItem = client.target(String.format("http://localhost:" + RULE.getLocalPort() + "/service/items/12"))
				.request()
				.get(ItemJson.class);
		
		assertThat(testItem.getItemid()).isNotNull();
		assertThat(testItem.getName()).isEqualTo(item.getName());
		
        
    }
	
}
