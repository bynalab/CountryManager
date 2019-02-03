package com.habeexdev.CountyRest.Service.Impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.habeexdev.CountyRest.Constant.AppConstants;
import com.habeexdev.CountyRest.Constant.ServerResponseStatus;
import com.habeexdev.CountyRest.Dto.ServerResponse;
import com.habeexdev.CountyRest.Dto.SignInRequest;
import com.habeexdev.CountyRest.Service.AccountService;
import com.habeexdev.CountyRest.Utility.Utility;



@Service
public class AcountServiceImpl implements AccountService{
	
	@Autowired
	AppConstants appConstants;
    
    
    @Autowired
  	BCryptPasswordEncoder passwordEncoder;
    
    Utility utility = new Utility();
	
	 private static Logger logger = LogManager.getLogger(AcountServiceImpl.class);
	
	
	@Override
	public ServerResponse login(SignInRequest request){
		
		ServerResponse response = new ServerResponse();
		try {
			
			logger.info(request.getUsername());
			
			//convert client id and client secret to base64 token 
			String authorization = Utility.getCredentials(appConstants.CLIENT_ID, appConstants.CLIENT_SECRET);
			logger.info(authorization);
			request.setGrant_type(appConstants.GRANT_TYPE);
			
			//send login request
			response = Utility.loginHttpRequest( appConstants.APP_LOGIN_URL, request, authorization);
			
		} catch (Exception e) {
			response.setData("Something went wrong !!!");
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		return response;
	}
	
	
	
}
