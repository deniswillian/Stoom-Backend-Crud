package com.stoom.backend;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.javafaker.Faker;
import com.stoom.backend.model.Address;
import com.stoom.backend.service.AddressService;
import com.stoom.backend.util.MapperObjectUtil;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressControllerTest {

	private MockMvc mockMvc;

	private Faker faker;

	private String mediaType = MediaType.APPLICATION_JSON_VALUE;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MapperObjectUtil mapperObjectUtil;

	@Autowired
	private AddressService addressService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		faker = new Faker();
	}

	@Test
	public void testCreateAddress() throws Exception {

		Address address = new Address();

		address.setStreetName("Rua Portugal");

		address.setNumber(faker.address().buildingNumber());

		address.setNeighbourhood("Jardim Lucélia");

		address.setCity("Sumaré");

		address.setState("São Paulo");

		address.setCountry("Brasil");

		address.setZipcode("13173215");

		String jsonContent = mapperObjectUtil.toJson(address);

		mockMvc.perform(MockMvcRequestBuilders.post("/address").contentType(mediaType).content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

	}

	@Test
	public void testGetAllAddress() throws Exception {

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/address").accept(mediaType))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		String response = mvcResult.getResponse().getContentAsString();

		List<Address> adresses = mapperObjectUtil.fromJsonList(response, Address.class);

		assertTrue(!adresses.isEmpty());
	}

	@Test
	public void testUpdateAddress() throws Exception {

		Address address = new Address();

		address.setStreetName("Rua Estados Unidos");

		address.setNumber(faker.address().buildingNumber());

		address.setNeighbourhood("Jardim Lucélia");

		address.setCity("Sumaré");

		address.setState("São Paulo");

		address.setCountry("Brasil");

		address.setZipcode("13173214");

		Address addressCreated = addressService.saveAddress(address);

		String callPath = "/address/" + addressCreated.getId();

		address.setCountry("BR");

		address.setComplement("Casa");

		String jsonContent = mapperObjectUtil.toJson(address);

		mockMvc.perform(MockMvcRequestBuilders.put(callPath).contentType(mediaType).content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

	}

	@Test
	public void testGetAddressById() throws Exception {

		Address address = new Address();

		address.setStreetName("Rua Ferreira Penteado");

		address.setNumber(faker.address().buildingNumber());

		address.setNeighbourhood("Centro");

		address.setCity("Campinas");

		address.setState("São Paulo");

		address.setCountry("Brasil");

		address.setZipcode("13010040");

		Address addressCreated = addressService.saveAddress(address);

		String callPath = "/address/" + addressCreated.getId();

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(callPath).accept(mediaType))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		String response = mvcResult.getResponse().getContentAsString();

		Address addressById = mapperObjectUtil.fromJson(response, Address.class);

		assertTrue(addressById.getCity().equals(address.getCity()));

	}
	
	@Test
	public void testDeleteAddressById() throws Exception {

		Address address = new Address();

		address.setStreetName("Av. Rebouças");

		address.setNumber("1835B");

		address.setNeighbourhood("Vila Santana");

		address.setCity("Sumaré");

		address.setState("São Paulo");

		address.setCountry("Brasil");

		address.setZipcode("13170275");

		Address addressCreated = addressService.saveAddress(address);

		String callPath = "/address/" + addressCreated.getId();

		mockMvc.perform(MockMvcRequestBuilders.delete(callPath).accept(mediaType))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}

	@Test
	public void testGetCoordinatesFromAddressNonexistent() throws Exception {

		Address address = new Address();

		address.setStreetName("***");

		address.setNumber("=====");

		address.setNeighbourhood(";;");

		address.setCity("][][");

		address.setState("####");

		address.setCountry("$$###");

		address.setZipcode("$$###");

		String jsonContent = mapperObjectUtil.toJson(address);

		mockMvc.perform(MockMvcRequestBuilders.post("/address").contentType(mediaType).content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

	}

}
