package com.test;



import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class PopulaitonReadRestClient {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		getCountries();

	//	postCountry();
		
		
//		getCountries();

		
	}
	
	private static void getCountries()
	{
	    final String uri = "http://localhost:8093/countries";
	     
	    RestTemplate restTemplate = new RestTemplate();
	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	     
	    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	    System.out.println(result);
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonInString = result.getBody();
	    Collection<Country> results=null;
		try 
		{
			results =  mapper.readValue(jsonInString, TypeFactory.defaultInstance().constructParametricType(Collection.class, Country.class ) );
			for (Country c: results)
			{
				System.out.println(c.getId() +" " + c.getCountryName() +" " + c.getPopulation());
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void postCountry()
	{
	    final String uri = "http://localhost:8093/countries";
	     
	    Country request = new Country(5,"Test",4444);
	    
	    
	    
	    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	    String json;
		try {
			
			json = ow.writeValueAsString(request);
		    RestTemplate restTemplate = new RestTemplate();
		     
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity entity = new HttpEntity(json, headers);
		     
		    ResponseEntity<Country> result = restTemplate.exchange(uri, HttpMethod.POST, entity, Country.class);
		    
		    System.out.println("Submitted the data *********");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void getEmployeesfromNativeJson()
	{
	    final String uri = "http://localhost:8093/countries";
	     
	    RestTemplate restTemplate = new RestTemplate();
	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	     
	    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	    System.out.println(result);
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonInString = result.getBody();
	    Collection<Country> results=null;
		try 
		{
			results =  mapper.readValue(jsonInString, TypeFactory.defaultInstance().constructParametricType(Collection.class, Country.class ) );
			for (Country c: results)
			{
				System.out.println(c.getId() +" " + c.getCountryName());
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
