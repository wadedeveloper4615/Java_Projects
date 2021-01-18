package com.wade.spring.homepage.nasa.service;

import com.wade.spring.homepage.nasa.service.data.neo.NearEarthObjects;
import com.wade.spring.homepage.nasa.service.data.rover.Photos;

public interface NasaService {
    Object getApod();

    NearEarthObjects getNeos();

    Photos getMarsPics();
}