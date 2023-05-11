package com.geocode.service;

import com.geocode.model.GeoLocation;
import com.geocode.model.Location;
import com.geocode.model.Results;

import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.Optional;

public interface GeoCodingService {

    Optional<GeoLocation> searchForLocation(String locationQueryString) throws URISyntaxException;

    Optional<Long> saveGeoLoaction(GeoLocation geoLocation);

    Optional<Long> saveCoordinates(Location location);

    Optional<Location> getCoordinatesById(Long id);
}
