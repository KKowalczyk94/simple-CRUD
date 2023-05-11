package com.geocode.configuration;

import com.geocode.service.GeoCodingServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final GeoCodingServiceImpl service;
    @Value("${test.address}")
    String testAddress;

    @Override
    public void run(String... args) throws Exception {
        log.info("Id of test address search.");
        log.info(String.valueOf(service.searchForLocation(testAddress).orElseThrow().getId()));
    }
}
