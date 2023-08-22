package com.techelevator.dao;

import com.techelevator.exception.ResourceNotFoundException;
import com.techelevator.model.Beer;
import com.techelevator.model.BeerRating;
import com.techelevator.model.BeerReview;

import java.util.List;

public interface BeerDao {

    List<Beer> getBeers(int breweryId);

    Beer getBeer(int id) throws ResourceNotFoundException;

    Beer addBeer(Beer newBeer);

    void linkBreweryBeer(int breweryId, int beerId);

    void updateBeer(Beer updatedBeer, int beerId);

    List<BeerRating> getRatingsByUser(int userId);

    List<BeerRating> getRatingsByBeerAndBrewery(int beerId, int breweryId) throws ResourceNotFoundException;

    int getAvgRatingByBeerId(int beerId) throws ResourceNotFoundException;

    BeerRating addBeerRating(BeerRating rating);

    List<BeerReview> getReviewsByUser(int userId);

    List<BeerReview> getReviewsByBeerAndBrewery(int beerId, int breweryId) throws ResourceNotFoundException;

    List<BeerReview> getReviewsByBeerId(int beerId) throws ResourceNotFoundException;

    BeerReview addBeerReview(BeerReview review);

    void unlinkBreweryBeer(int beerId);

    void deleteReviews(int beerId);

    void deleteRatings(int beerId);

    void deleteBeer(int beerId);

}
