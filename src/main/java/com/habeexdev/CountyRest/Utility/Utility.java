package com.habeexdev.CountyRest.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Random;

import com.google.gson.Gson;
import com.habeexdev.CountyRest.Constant.ServerResponseStatus;
import com.habeexdev.CountyRest.Dto.LoginResponse;
import com.habeexdev.CountyRest.Dto.ServerResponse;
import com.habeexdev.CountyRest.Dto.SignInRequest;
import com.habeexdev.CountyRest.Model.Country;


public class Utility {
	
	public static boolean containsName(final Collection<Country> list, final String name){
	    return list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
	}
	
	
	public static int generateDigit (){
		Random random = new Random();
		int numb = random.nextInt(90000);
		return numb;
	}
	
	    /**
	     * Login request method, this method login as oAuth login token requires basic authorization set 
	     * @param urlPath
	     * @param request
	     * @param authorization
	     * @return ServerResponse 
	     */
	    public static ServerResponse loginHttpRequest(String urlPath, SignInRequest request, String authorization) {
			try {
			
			ServerResponse response = new ServerResponse();
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection)
			
			url.openConnection();
	         
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization", authorization);
			
			Gson gson = new Gson();
			String input  = "grant_type=" + URLEncoder.encode(request.getGrant_type()) + 
					"&username=" + URLEncoder.encode(request.getUsername()) +
					"&password=" + URLEncoder.encode(request.getPassword()) ;
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				
				BufferedReader br = new BufferedReader(new
				
				
				InputStreamReader((conn.getInputStream())));
				String output;
				while ((output = br.readLine()) != null) {
				
					conn.disconnect();
					
					LoginResponse loginResponse = gson.fromJson(output, LoginResponse.class);
					response.setData(loginResponse);
					response.setMessage("Login Successful");
			        response.setSuccess(true);
					response.setStatus(ServerResponseStatus.OK);
					return response;
			
				}
			
			}else if (conn.getResponseCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
				
				       BufferedReader br = new BufferedReader(new
						
						
						InputStreamReader((conn.getErrorStream())));
						String output;
						while ((output = br.readLine()) != null) {
							
							conn.disconnect();
							response.setData("Failed to sign in");
							response.setMessage("Invalid username or password");
					        response.setSuccess(false);
							response.setStatus(ServerResponseStatus.FAILED);
							return response;
					 
						}
			}else if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
				
			       BufferedReader br = new BufferedReader(new
					
					
					InputStreamReader((conn.getErrorStream())));
					String output;
					while ((output = br.readLine()) != null) {
						
						conn.disconnect();
						response.setData("You are not authorize ");
						response.setStatus(ServerResponseStatus.FAILED);
						return response;
				 
					}
			}
				
			} catch (MalformedURLException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			
			}
				return null;
			
		}
		
		
	    /**
	     * Convert credential to basic auth Base64 String format
	     * @param username
	     * @param password
	     * @return String
	     */
		public static String getCredentials(String username, String password) {
	        String plainClientCredentials = username + ":" + password;
	        String base64ClientCredentials = new String(org.apache.commons.codec.binary.Base64.encodeBase64(plainClientCredentials.getBytes()));

	        String authorization = "Basic " + base64ClientCredentials;
	        return authorization;
	    } 

}
