package com.geocode;

import com.geocode.model.Location;
import jakarta.persistence.Column;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
@Component
public class Utils {

    public URI createUri(String locationQueryString) throws URISyntaxException {
        String scheme = "https";
        String host = "maps.googleapis.com";
        String path = "/maps/api/geocode/json";
        String addressParam = createAddressParam(locationQueryString);
        String key = "AIzaSyDV6J_lwZ8KtNQg_1DFdJLKQRPjrlxCm4E";
        URIBuilder uriBuilder = new URIBuilder();
        return uriBuilder.setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .setParameter("address", addressParam)
                .setParameter("key", key).build();
    }

    private String createAddressParam(String locationQueryString) {
        return ("24 " + locationQueryString).replace(" ", "%20");
    }
}
