package com.habeexdev.CountyRest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.habeexdev.CountyRest.Dto.CountryDto;
import com.habeexdev.CountyRest.Dto.SignInRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
classes = CountyRestApplication.class)
@AutoConfigureMockMvc
public class CountyRestApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	CountryDto countryDto;
	SignInRequest signInRequest;
	
	
	Gson gson;
	
	String authorization = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidXNlci13ZWItc2VydmljZSJdLCJ1c2VyX25hbWUiOiJoYWJlZXgiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTQ5Mjc1NTY5LCJhdXRob3JpdGllcyI6WyJhZG1pbiJdLCJqdGkiOiJmYTllMzdhMS1hNjYzLTQxMjQtOGYwMy1hMTQwYThmODNlODUiLCJjbGllbnRfaWQiOiJ1c2VyIn0.PeforvGHtuj6AF4k0EVGlknvwLSH8S9FASP3TvzoxPc";

	@Before
	  public void setup() {
		countryDto = new CountryDto();
		countryDto.setCountryName("Nigeria");
		
		signInRequest = new SignInRequest();
		signInRequest.setPassword("password");
		signInRequest.setUsername("habeex");
		gson = new Gson();
	  }
	
	

	
	@Test
	public void getCountries() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/country")
					.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		System.out.println(mvcResult.getResponse());
		
	}
	
	@Test
	public void addCountry() throws Exception {
		String json = gson.toJson(countryDto);
		
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.put("/country")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", authorization)
				.content(json)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		System.out.println(mvcResult.andReturn().getResponse());
		
	}
	
	
	@Test
	public void deleteCountry() throws Exception {
		
	String json = gson.toJson(countryDto);
		
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.delete("/country")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", authorization)
				.content(json)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		System.out.println(mvcResult.andReturn().getResponse());
		
	}
	
	@Test
	public void Login() throws Exception {
		String json = gson.toJson(signInRequest);
		
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		System.out.println(mvcResult.andReturn().getResponse());
		
	}

}

