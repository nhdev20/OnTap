package com.techelevator.dao;

import com.techelevator.exception.ResourceNotFoundException;
import com.techelevator.model.Brewery;
import com.techelevator.model.DayHours;
import com.techelevator.openbrewerydb.model.OpenBreweryDTO;

import java.util.List;

public interface BreweryDao {

    List<Brewery> getBreweries();

    Brewery getBreweryById(Integer id) throws ResourceNotFoundException;

    Brewery getBreweryByOpenDbId(String openDbId);

    void addBreweryFromOpenDb(OpenBreweryDTO brewery);

    void updateBrewery(Brewery brewery, Integer id);

    List<DayHours> getBreweryHours(int breweryId);

    void updateBreweryDays(int breweryId, DayHours day);

}
