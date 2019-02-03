package com.habeexdev.CountyRest.Service;

import com.habeexdev.CountyRest.Dto.CountryDto;
import com.habeexdev.CountyRest.Dto.ServerResponse;

public interface CountryService {

	public ServerResponse getCountries();
	
	public ServerResponse addCountry(CountryDto countryDto);
	
	public ServerResponse deleteCountry(CountryDto countryDto);

}
