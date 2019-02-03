package com.habeexdev.CountyRest.Service.Impl;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.habeexdev.CountyRest.Constant.ServerResponseStatus;
import com.habeexdev.CountyRest.Dto.CountryDto;
import com.habeexdev.CountyRest.Dto.ServerResponse;
import com.habeexdev.CountyRest.Model.Country;
import com.habeexdev.CountyRest.Service.CountryService;
import com.habeexdev.CountyRest.Utility.Utility;

@Service
public class CountryServiceImpl implements CountryService {
	
	Collection<Country> countries = new HashSet<>();

	@Override
	public ServerResponse addCountry(CountryDto countryDto) {
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			Country country = new Country();
			country.setName(countryDto.getCountryName());
			country.setCode(String.valueOf(Utility.generateDigit()));
			
			
			if (Utility.containsName(countries, country.getName())) {
				response.setData("Country name already exist");
				response.setMessage("Country name already exist");
		        response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
	            return response;
			}

			countries.add(country);
			
			response.setData(country);
			response.setMessage("Country name successfully added to the list");
	        response.setSuccess(true);
            response.setStatus(ServerResponseStatus.OK);
			
			
		} catch (Exception e) {
			response.setData("Something went wrong !!!");
			response.setMessage("Something went wrong !!!");
			 response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		return response;
	}
	
	@Override
	public ServerResponse deleteCountry(CountryDto countryDto) {
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			if (!Utility.containsName(countries, countryDto.getCountryName())) {
				response.setData("Country name does not exist");
				response.setMessage("Country name does not exist");
		        response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
	            return response;
			}

			countries.removeIf(obj -> obj.getName().contentEquals(countryDto.getCountryName()));
			
			response.setData(countryDto.getCountryName());
			response.setMessage("Country name successfully deleted");
	        response.setSuccess(true);
            response.setStatus(ServerResponseStatus.OK);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setData("Something went wrong !!!" + e.getMessage());
			response.setMessage("Something went wrong !!!");
			 response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		return response;
	}
	
	@Override
	public ServerResponse getCountries() {
		ServerResponse response = new ServerResponse();
		
		try {
			
			if(countries == null || countries.isEmpty()){
				
				response.setData("Country list is empty, please add country of your choice using put request");
				response.setMessage("Country list is empty, please add country of your choice using put request");
		        response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
	            return response;
			}
			
			response.setData(countries);
			response.setMessage("Request successfull");
	        response.setSuccess(true);
            response.setStatus(ServerResponseStatus.OK);
			
			
		} catch (Exception e) {
			response.setData("Something went wrong !!!");
			response.setMessage("Something went wrong !!!");
			 response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		return response;
	}
	

}
