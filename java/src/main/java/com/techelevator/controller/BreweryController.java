package com.techelevator.controller;

import com.techelevator.exception.*;
import com.techelevator.model.*;
import com.techelevator.openbrewerydb.exception.OpenBreweryDBException;
import com.techelevator.openbrewerydb.model.OpenBreweryDTO;
import com.techelevator.service.BeerService;
import com.techelevator.service.BreweryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
//@PreAuthorize("isAuthenticated()")
public class BreweryController {

    private final BreweryService breweryService;
    private final BeerService beerService;

    public BreweryController(BreweryService breweryService, BeerService beerService) {
        this.breweryService = breweryService;
        this.beerService = beerService;
    }

    @GetMapping("/breweries")
    public List<Brewery> getBreweries() {
        return breweryService.getBreweries();
    }

    @GetMapping("/breweries/open")
    public List<Brewery> getBreweriesOpen(@RequestParam(required = false) String city) {
        try {
            return breweryService.getOpenDBBreweries(city);
        } catch (OpenBreweryDBException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/breweries/{id}")
    public Brewery getBreweryById(@PathVariable Integer id) {
        try {
            return breweryService.getBreweryById(id);
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping("/breweries/{id}")
    public void updateBrewery(@RequestBody Brewery brewery, @PathVariable Integer id) {
        if (id != brewery.getId()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Id in URL did not match Id in Brewery object");
        }
        try {
            breweryService.updateBrewery(brewery, id);
        } catch (DaoException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }

    @GetMapping("/breweries/{id}/beers")
    public List<Beer> getBeers(@PathVariable Integer id) {
        return beerService.getBeers(id);
    }

    @GetMapping("/beers/{id}")
    public Beer getBeerById(@PathVariable Integer id) {
        try {
            return beerService.getBeerById(id);
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/breweries/{id}/beers")
    public Beer addBeer(@RequestBody Beer newBeer, @PathVariable Integer id) {
        try {
            return beerService.addBeer(newBeer, id);
        } catch (CreationFailureException | LinkFailureException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


    @PutMapping("beers/{id}")
    public void updateBeer(@RequestBody Beer updatedBeer, @PathVariable Integer id) {
        if (id != updatedBeer.getId()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Id in URL did not match Id in Beer object");
        }
        try {
            beerService.updateBeer(updatedBeer, id);
        } catch (DaoException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/beers/ratings/{userId}")
    public List<BeerRating> getRatingsByUser(@PathVariable Integer userId) {
        return beerService.getRatingsByUser(userId);
    }

    @GetMapping("/ratings/{userId}")
    public List<RatingBeer> getRatingsWithBeerByUser(@PathVariable Integer userId) throws ResourceNotFoundException{
        return beerService.getRatingsWithBeerByUser(userId);
    }

    @GetMapping("breweries/{breweryId}/beers/ratings/{beerId}")
    public List<BeerRating> getRatingsByBeerAndBrewery(@PathVariable Integer beerId, @PathVariable Integer breweryId) {
        try {
            return beerService.getRatingsByBeerAndBrewery(beerId, breweryId);
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/beers/{beerId}/rating")
    public int getAvgRatingByBeerId(@PathVariable Integer beerId) {
        try {
            return beerService.getAvgRatingByBeerId(beerId);
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/beers/ratings")
    public BeerRating addRating(@RequestBody BeerRating newRating) {
        try {
            return beerService.addRating(newRating);
        } catch (DuplicateRatingException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        } catch (CreationFailureException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/beers/reviews/{userId}")
    public List<BeerReview> getReviewsByUser(@PathVariable Integer userId) {

        return beerService.getReviewsByUser(userId);
    }

    @GetMapping("/reviews/{userId}")
    public List<ReviewBeer> getReviewsWithBeerByUser(@PathVariable Integer userId) throws ResourceNotFoundException{
        return beerService.getReviewsWithBeerByUser(userId);
    }

    @GetMapping("breweries/{breweryId}/beers/reviews/{beerId}")
    public List<BeerReview> getReviewsByBeerAndBrewery(@PathVariable Integer beerId, @PathVariable Integer breweryId) {
        try {
            return beerService.getReviewsByBeerAndBrewery(beerId, breweryId);
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/beers/{beerId}/reviews")
    public List<BeerReview> getReviewsByBeerId(@PathVariable Integer beerId) {
        try {
            return beerService.getReviewsByBeerId(beerId);
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/beers/reviews")
    public BeerReview addReview(@RequestBody BeerReview newReview) {
        try {
            return beerService.addReview(newReview);
        } catch (DuplicateReviewException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        } catch (CreationFailureException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/beers/{id}")
    public void deleteBeer(@PathVariable int id) {
        try {
            beerService.deleteBeer(id);
        } catch (DaoException ex) {
            throw new DaoException(ex.getMessage());
        }
    }


}

