package com.geocode.controller;

import com.geocode.model.GeoLocation;
import com.geocode.model.Location;
import com.geocode.model.Results;
import com.geocode.service.GeoCodingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class WeatherControllerDBImplementation implements WeatherControllerAPI {
    private final GeoCodingServiceImpl service;

    @Override
    public ResponseEntity<GeoLocation> searchForLocation(String locationQueryString) throws URISyntaxException {
        return  ResponseEntity.ok(service.searchForLocation(locationQueryString).orElseThrow());
    }

    @Override
    public ResponseEntity<Location> getLocationById(Long id) {
        return ResponseEntity.ok(service.getCoordinatesById(id).orElseThrow());
    }

    @Override
    public ResponseEntity<Long> savelocation(GeoLocation geoLocation) {
        return ResponseEntity.ok(service.saveGeoLoaction(geoLocation).orElseThrow());
    }
    //getResults().get(0).getGeometry().getLocation()
    @Override
    public ResponseEntity<Long> saveCoordinates(Location location) {
        return ResponseEntity.ok(service.saveCoordinates(location).orElseThrow());
    }


}
