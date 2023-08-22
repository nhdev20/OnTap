package com.techelevator.openbrewerydb.service;

import com.techelevator.openbrewerydb.exception.OpenBreweryDBException;
import com.techelevator.openbrewerydb.model.OpenBreweryDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenBreweryDBService {

    private static final String OPEN_BREWERY_DB_BASE_URL = "https://api.openbrewerydb.org/v1/breweries";

    private final RestTemplate restTemplate = new RestTemplate();

    public OpenBreweryDTO[] getOpenBreweryList() {
        try {
            OpenBreweryDTO[] breweries = restTemplate.getForObject(OPEN_BREWERY_DB_BASE_URL, OpenBreweryDTO[].class);
            return breweries;
        } catch (RestClientResponseException | ResourceAccessException ex) {
            throw new OpenBreweryDBException("Error retrieving breweries");
        }
    }

    public OpenBreweryDTO[] getOpenBreweryListByCity(String city) {
        try {
            OpenBreweryDTO[] breweries = restTemplate.getForObject(OPEN_BREWERY_DB_BASE_URL + "?by_city=" + city, OpenBreweryDTO[].class);
            return breweries;
        } catch (RestClientResponseException | ResourceAccessException ex) {
            throw new OpenBreweryDBException("Error retrieving breweries by city");
        }
    }

}
