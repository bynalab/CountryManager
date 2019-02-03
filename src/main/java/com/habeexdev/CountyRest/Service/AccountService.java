package com.habeexdev.CountyRest.Service;

import org.springframework.stereotype.Service;

import com.habeexdev.CountyRest.Dto.ServerResponse;
import com.habeexdev.CountyRest.Dto.SignInRequest;

@Service
public interface AccountService {

	
	public ServerResponse login(SignInRequest request);
	

	
}
