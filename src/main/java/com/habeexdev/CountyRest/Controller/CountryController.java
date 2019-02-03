package com.habeexdev.CountyRest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.habeexdev.CountyRest.Constant.ServerResponseStatus;
import com.habeexdev.CountyRest.Dto.CountryDto;
import com.habeexdev.CountyRest.Dto.ServerResponse;
import com.habeexdev.CountyRest.Service.CountryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/country", produces = "application/json")
@Api(tags = "Country Management", description = "Endpoint")
public class CountryController {

	@Autowired
	CountryService countryService;
	
	private HttpHeaders responseHeaders = new HttpHeaders();	
		
	@ApiOperation(value = "Get all coutries", response = ServerResponse.class)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> getCountries(@RequestHeader("Authorization")  String authorization){
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = countryService.getCountries();
		
		} catch (Exception e) {
			response.setData("An error occured in user account verification" + e.getMessage());
			response.setMessage("An error occured in user account verification");
	        response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}
	
	@ApiOperation(value = "add country name", response = ServerResponse.class)
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> addCountry(@RequestHeader("Authorization")  String authorization, @RequestBody CountryDto dto){
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = countryService.addCountry(dto);
		
		} catch (Exception e) {
			response.setData("An error occured in user account verification" + e.getMessage());
			response.setMessage("An error occured in user account verification");
	        response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}
	
	@ApiOperation(value = "delete", response = ServerResponse.class)
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deleteCountry(@RequestHeader("Authorization")  String authorization, @RequestBody CountryDto dto){
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = countryService.deleteCountry(dto);
		
		} catch (Exception e) {
			response.setData("An error occured in user account verification" + e.getMessage());
			response.setMessage("An error occured in user account verification");
	        response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}
}
