package com.geocode.service;

import com.geocode.Utils;
import com.geocode.model.GeoLocation;
import com.geocode.model.Location;
import com.geocode.repository.GeoLocationRepository;
import com.geocode.repository.LocationRepository;
import com.geocode.repository.ResutsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeoCodingServiceImpl implements GeoCodingService {
    private final LocationRepository locationRepository;
    private final ResutsRepository resutsRepository;
    private final GeoLocationRepository geoLocationRepository;
    private final Utils utils;

    @Override
    public Optional<GeoLocation> searchForLocation(String locationQueryString) throws URISyntaxException {
        // https://maps.googleapis.com/maps/api/geocode/json?address=24%20Cybernetyki%209%20Warszawa%20PL&key=AIzaSyDV6J_lwZ8KtNQg_1DFdJLKQRPjrlxCm4E
        URI uri = utils.createUri(locationQueryString);
        RestTemplate restTemplate = new RestTemplate();
        return Optional.ofNullable(restTemplate.getForObject(uri, GeoLocation.class));
    }

    @Override
    public Optional<Long> saveGeoLoaction(GeoLocation geoLocation) {
        return Optional.of(geoLocationRepository.save(geoLocation).getId());
    }

    @Override
    public Optional<Long> saveCoordinates(Location location) {
        return Optional.ofNullable(locationRepository.save(location).getId());
    }

    @Override
    public Optional<Location> getCoordinatesById(Long id) {
        return Optional.of(locationRepository.findById(id).orElseThrow());
    }


}
