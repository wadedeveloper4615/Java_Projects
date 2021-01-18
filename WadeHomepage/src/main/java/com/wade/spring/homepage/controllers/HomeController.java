package com.wade.spring.homepage.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wade.spring.homepage.nasa.service.NasaService;
import com.wade.spring.homepage.nasa.service.data.neo.NearEarthObjects;
import com.wade.spring.homepage.nasa.service.data.rover.Photos;

@Controller
public class HomeController {
	private final static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
    private NasaService service;
	
	@RequestMapping("/")
    public String index() {
        return "index";
    }
	
	@RequestMapping(value = "/apod")
    public Object getApod(){
        return service.getApod();
    }

	@RequestMapping(value = "/neo")
    public Object getNeos(Model model){
		NearEarthObjects detail = service.getNeos();
        model.addAttribute("data", detail);
        return "neo";
    }

	@RequestMapping(value = "/roverphotos")
    public String getMarsPics(Model model){
        Photos photos = service.getMarsPics();
        model.addAttribute("data", photos);
        return "mars";
    }
}
