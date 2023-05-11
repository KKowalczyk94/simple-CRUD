package com.geocode.controller;

import com.geocode.model.GeoLocation;
import com.geocode.model.Location;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;


@RequestMapping(value = "/api/storage/db", produces = MediaType.APPLICATION_JSON_VALUE)
public interface WeatherControllerAPI {

    @GetMapping("/location/{locationQueryString}")
    ResponseEntity<GeoLocation> searchForLocation(@PathVariable String locationQueryString) throws URISyntaxException;

    @GetMapping("/getLocation")
    ResponseEntity<Location> getLocationById(@RequestParam Long id);

    @PostMapping("/saveLocation")
    ResponseEntity<Long> savelocation(@RequestBody GeoLocation geoLocation);

    @PostMapping("saveCoordinates")
    ResponseEntity<Long> saveCoordinates(@RequestBody Location location);
}
