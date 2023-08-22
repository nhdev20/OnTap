package com.techelevator.service;

import com.techelevator.dao.BeerDao;
import com.techelevator.exception.*;
import com.techelevator.model.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerService {

    private BeerDao beerDao;

    public BeerService(BeerDao beerDao) {
        this.beerDao = beerDao;
    }

    public List<Beer> getBeers(int breweryId) {
        return beerDao.getBeers(breweryId);
    }

    public Beer getBeerById(Integer id) throws ResourceNotFoundException{
        return beerDao.getBeer(id);
    }

    public Beer addBeer(Beer newBeer, Integer breweryId) {
        Beer savedbeer = null;
        try {
            savedbeer = beerDao.addBeer(newBeer);
        } catch (DaoException ex) {
            throw new CreationFailureException(ex.getMessage());
        }
        linkBreweryBeer(newBeer, breweryId);
        return savedbeer;
    }

    public void linkBreweryBeer(Beer beer, Integer breweryId) {
        try {
            beerDao.linkBreweryBeer(breweryId, beer.getId());
        } catch (DaoException ex) {
            throw new LinkFailureException(ex.getMessage());
        }
    }

    public void updateBeer(Beer updatedBeer, int beerId) {
        beerDao.updateBeer(updatedBeer, beerId);
    }

    public List<BeerRating> getRatingsByUser(int userId) {
        return beerDao.getRatingsByUser(userId);
    }

    public List<RatingBeer> getRatingsWithBeerByUser(int userId) throws ResourceNotFoundException {
        List<RatingBeer> returnList = new ArrayList<>();

        List<BeerRating> ratings = beerDao.getRatingsByUser(userId);

        for (BeerRating rating: ratings) {
            Beer beer = beerDao.getBeer(rating.getBeerId());
            RatingBeer item = new RatingBeer();

            item.setUserId(rating.getUserId());
            item.setBeerId(rating.getBeerId());
            item.setRating(rating.getAmount());
            item.setName(beer.getName());
            item.setDescription(beer.getDescription());
            item.setAbv(beer.getAbv());
            item.setType(beer.getType());

            returnList.add(item);
        }
        return returnList;
    }


    public List<BeerRating> getRatingsByBeerAndBrewery(int beerId, int breweryId) throws ResourceNotFoundException {
        return beerDao.getRatingsByBeerAndBrewery(beerId, breweryId);
    }

    public int getAvgRatingByBeerId(int beerId) throws ResourceNotFoundException{
        return beerDao.getAvgRatingByBeerId(beerId);
    }

    public BeerRating addRating(BeerRating newRating) throws DuplicateRatingException {
        try {
            List<BeerRating> existingRatingsForUser = getRatingsByUser(newRating.getUserId());
            for (BeerRating rating : existingRatingsForUser) {
                if (rating.getBeerId() == newRating.getBeerId()) {
                    throw new DuplicateRatingException("You have already rated this beer");
                }
            }
            return beerDao.addBeerRating(newRating);
        } catch (DaoException ex) {
            throw new CreationFailureException(ex.getMessage());
        }
    }

    public List<BeerReview> getReviewsByUser(int userId) {
        return beerDao.getReviewsByUser(userId);
    }

    public List<ReviewBeer> getReviewsWithBeerByUser(int userId) throws ResourceNotFoundException{
        List<ReviewBeer> returnList = new ArrayList<>();

        List<BeerReview> reviews = beerDao.getReviewsByUser(userId);

        for (BeerReview review: reviews) {
            Beer beer = beerDao.getBeer(review.getBeerId());
            ReviewBeer item = new ReviewBeer();

            item.setUserId(review.getUserId());
            item.setBeerId(review.getBeerId());
            item.setReview(review.getDescription());
            item.setName(beer.getName());
            item.setDescription(beer.getDescription());
            item.setAbv(beer.getAbv());
            item.setType(beer.getType());

            returnList.add(item);
        }
        return returnList;
    }

    public List<BeerReview> getReviewsByBeerAndBrewery(int beerId, int breweryId) throws ResourceNotFoundException {
        return beerDao.getReviewsByBeerAndBrewery(beerId, breweryId);
    }

    public List<BeerReview> getReviewsByBeerId(int beerId) throws ResourceNotFoundException{
        return beerDao.getReviewsByBeerId(beerId);
    }


    public BeerReview addReview(BeerReview newReview) throws DuplicateReviewException {
        try {
            List<BeerReview> existingReviewsForUser = getReviewsByUser(newReview.getUserId());
            for (BeerReview review : existingReviewsForUser) {
                if (review.getBeerId() == newReview.getBeerId()) {
                    throw new DuplicateReviewException("You have already reviewed this beer");
                }
            }
            return beerDao.addBeerReview(newReview);
        } catch (DaoException ex) {
            throw new CreationFailureException(ex.getMessage());
        }
    }

    public void deleteBeer(int id) {
        try {
            beerDao.unlinkBreweryBeer(id);
            beerDao.deleteReviews(id);
            beerDao.deleteRatings(id);
            beerDao.deleteBeer(id);
        } catch (DaoException ex){
            throw new DaoException("Error encountered while attempting to delete.");
        }
    }
}
