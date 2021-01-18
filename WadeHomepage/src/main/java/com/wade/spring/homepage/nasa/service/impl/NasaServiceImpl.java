package com.wade.spring.homepage.nasa.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wade.spring.homepage.controllers.HomeController;
import com.wade.spring.homepage.nasa.service.NasaService;
import com.wade.spring.homepage.nasa.service.data.neo.NearEarthObjects;
import com.wade.spring.homepage.nasa.service.data.rover.Photos;

@Service
public class NasaServiceImpl implements NasaService {
	private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

	public String getKey() {
		return "Qp2GwywgE325c1xmeGOyGGpKmoKNrD2oVFt8Nto3";
	}

	public JSONObject getApod() {
		String url = "https://api.nasa.gov/planetary/apod?api_key=" + getKey();

		// we shall make our first api call :
		HttpHeaders httpHeaders = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<>("", httpHeaders);
		ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.GET, request, JSONObject.class);

		JSONObject body = response.getBody();
		
		logger.info(body.toString());
		
		return body;
	}

	public NearEarthObjects getNeos() {
		Date dt = new Date();
		DateTime dtOrg = new DateTime(dt);
		DateTime dtPlusOne = dtOrg.plusDays(1);
		String today = dtPlusOne.toString().substring(0, 10);
		String date_7daysAfter = dtOrg.plusDays(5).toString().substring(0, 10);
		String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date=" + today + "&end_date=" + date_7daysAfter +"&";

		NearEarthObjects detail = getDetail2(url, NearEarthObjects.class);

		logger.info(detail.toString());
		
		return null;
	}

	public Photos getMarsPics() {
		String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&";
		
		Photos detail = getDetail(url, Photos.class);

		logger.info(detail.toString());
		
		return detail;
	}
	
	public <T> T getDetail(String url, Class<T> result){
		RestTemplate restTemplate = new RestTemplate();
		url += "api_key=" + getKey();
		logger.info("getting data from url = "+url);
		return restTemplate.getForObject(url, result);
	}
	
	public <T> T getDetail2(String url, Class<T> result){
		RestTemplate restTemplate = new RestTemplate();
		url += "api_key=" + getKey();
		logger.info("getting data from url = "+url);
		
		ParameterizedTypeReference<T> responseType = new ParameterizedTypeReference<>() {};
		ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
		
		//return restTemplate.getForObject(url, result);
		return responseEntity.getBody();
	}
}
