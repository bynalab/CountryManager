package com.habeexdev.CountyRest.Controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.habeexdev.CountyRest.Constant.ServerResponseStatus;
import com.habeexdev.CountyRest.Dto.ServerResponse;
import com.habeexdev.CountyRest.Dto.SignInRequest;
import com.habeexdev.CountyRest.Service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/user", produces = "application/json")
@Api(tags = "User Management", description = "Endpoint")
public class AccountController {
	

	@Autowired
	AccountService accountService;
	
	private HttpHeaders responseHeaders = new HttpHeaders();	
		
	@ApiOperation(value = "Login user account", response = ServerResponse.class)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> Login(@RequestBody SignInRequest request){
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = accountService.login(request);
		
		} catch (Exception e) {
			response.setData("An error occured in user account verification" + e.getMessage());
			response.setMessage("An error occured in user account verification");
	        response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}
	
}
