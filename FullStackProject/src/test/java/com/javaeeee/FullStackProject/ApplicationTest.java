package com.javaeeee.FullStackProject;

import com.javaeeee.FullStackProject.representations.ItemJson;
import com.javaeeee.FullStackProject.representations.ProfileJson;
import com.javaeeee.entities.Address;
import com.javaeeee.entities.Item;
import com.javaeeee.entities.Profile;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.fest.assertions.api.Assertions.assertThat;


// Test resources in web app
public class ApplicationTest {

	private Client client;
	private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test.yml");
	
	
	private ItemJson item = new ItemJson(new Item("12", "Test Item", "Washington DC","20",100,0,5));
	private ProfileJson testProfile = new ProfileJson(new Profile("29", "1234", "Mike", "Rothkopf","555-555-5555","test@test.com", new Address("144 Elm St","Washington", "DC", "20001")));
	
	@ClassRule
    public static final DropwizardAppRule<FullStackProjectConfiguration> RULE =
            new DropwizardAppRule<FullStackProjectConfiguration>(FullStackProjectApplication.class, CONFIG_PATH);
	
	
	@Before
    public void setUp() {
      client = ClientBuilder.newClient();
    }
	
	
	@Test
	public void createandRetrieveProfile() {
		
		// Test profile creation
		Response response = client.target(String.format("http://localhost:" + RULE.getLocalPort() + "/service/profiles/create"))
				.request()
				.post(Entity.entity(testProfile, MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatus()).isEqualTo(201);
		
		// Confirm profile creation and retrieve profile
		
		final ProfileJson profTest = client.target(String.format("http://localhost:" + RULE.getLocalPort() + "/service/profiles/24"))
				.request()
				.get(ProfileJson.class);
		
		assertThat(profTest.getUserid()).isNotNull();
		assertThat(profTest.getFirstName()).isEqualTo(testProfile.getFirstName());
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
		// TODO: Update this test with security feature
		/*final ItemJson testItem = client.target(String.format("http://localhost:" + RULE.getLocalPort() + "/service/items/12"))
				.request()
				.get(ItemJson.class);
		
		assertThat(testItem.getItemid()).isNotNull();
		assertThat(testItem.getName()).isEqualTo(item.getName());*/
		
    }
	
}
