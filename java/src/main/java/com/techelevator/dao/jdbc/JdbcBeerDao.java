package com.techelevator.dao.jdbc;

import com.techelevator.dao.BeerDao;
import com.techelevator.dao.jdbc.mapper.BeerMapper;
import com.techelevator.dao.jdbc.mapper.RatingsMapper;
import com.techelevator.dao.jdbc.mapper.ReviewsMapper;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ResourceNotFoundException;
import com.techelevator.model.Beer;
import com.techelevator.model.BeerRating;
import com.techelevator.model.BeerReview;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcBeerDao implements BeerDao {
    private final JdbcTemplate jdbcTemplate;

    private final BeerMapper beerMapper = new BeerMapper();
    private final RatingsMapper ratingsMapper = new RatingsMapper();
    private final ReviewsMapper reviewsMapper = new ReviewsMapper();

    public JdbcBeerDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Beer> getBeers(int breweryId) {
        String sql = "SELECT * FROM beer\n" +
                "WHERE beer_id IN (SELECT beer_id FROM brewery_beer \n" +
                "WHERE brewery_id = ?);";
        try {
            return jdbcTemplate.query(sql, new Object[]{breweryId}, beerMapper);
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    public Beer getBeer(int id) throws ResourceNotFoundException{

        String sql = "SELECT * FROM beer\n" +
                "WHERE beer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                Beer beer = new Beer();
                beer.setId(results.getInt("beer_id"));
                beer.setName(results.getString("beer_name"));
                beer.setDescription(results.getString("description"));
                beer.setAbv(results.getDouble("abv"));
                beer.setType(results.getString("beer_type"));
                return beer;
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }

    }

    public Beer addBeer(Beer newBeer) {
        String sql = "INSERT INTO beer VALUES (DEFAULT, ?, ?, ?, ?) RETURNING beer_id";
        try {
            Integer newBeerId = jdbcTemplate.queryForObject(sql, Integer.class, newBeer.getName(), newBeer.getDescription(),
                    newBeer.getAbv(), newBeer.getType());
            newBeer.setId(newBeerId);
            return newBeer;
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public void linkBreweryBeer(int breweryId, int beerId) {
        String sql = "INSERT INTO brewery_beer VALUES (?, ?)";
        try {
            int rowAdded = jdbcTemplate.update(sql, breweryId, beerId);
            if (rowAdded == 0) {
                throw new DaoException("No records inserted");
            }
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public void updateBeer(Beer updatedBeer, int beerId) {
        String sql = "UPDATE beer SET beer_name = ?, description = ?, abv = ?, beer_type = ?" +
                "WHERE beer_id = ?";
        try {
            jdbcTemplate.update(sql, updatedBeer.getName(), updatedBeer.getDescription(),
                    updatedBeer.getAbv(), updatedBeer.getType(), beerId);
        }  catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public List<BeerRating> getRatingsByUser(int userId) {
        String sql = "SELECT * FROM rating WHERE user_id = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{userId}, ratingsMapper);
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    public List<BeerRating> getRatingsByBeerAndBrewery(int beerId, int breweryId) throws ResourceNotFoundException {
        String sql = "SELECT * FROM rating r\n" +
                "JOIN beer b ON r.beer_id = b.beer_id\n" +
                "JOIN brewery_beer b_b on b_b.beer_id = b.beer_id\n" +
                "WHERE b.beer_id = ? AND b_b.brewery_id = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{beerId, breweryId}, ratingsMapper);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Couldn't find beer with id " + beerId);
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public int getAvgRatingByBeerId(int beerId) throws ResourceNotFoundException{
        String sql ="SELECT AVG(amount) FROM rating\n" +
                "WHERE beer_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
            if (results.next()) {
                int averageRating = (int)(results.getDouble("avg"));
                if (averageRating > 0) {
                    return averageRating;
                } else {
                    throw new ResourceNotFoundException();
                }
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (DataAccessException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public BeerRating addBeerRating(BeerRating newRating) {
        String sql = "INSERT INTO rating VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, newRating.getUserId(), newRating.getBeerId(),
                    newRating.getAmount());
            return newRating;
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public List<BeerReview> getReviewsByUser(int userId) {
        String sql = "SELECT * FROM review WHERE user_id = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{userId}, reviewsMapper);
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    public List<BeerReview> getReviewsByBeerAndBrewery(int beerId, int breweryId) throws ResourceNotFoundException {
        String sql = "SELECT * FROM review r\n" +
                "JOIN beer b ON r.beer_id = b.beer_id\n" +
                "JOIN brewery_beer b_b on b_b.beer_id = b.beer_id\n" +
                "WHERE b.beer_id = ? AND b_b.brewery_id = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{beerId, breweryId}, reviewsMapper);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Couldn't find beer with id " + beerId);
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public List<BeerReview> getReviewsByBeerId(int beerId) throws ResourceNotFoundException {
        String sql = "SELECT * FROM review\n" +
                "WHERE beer_id = ?;";
        try {
            return jdbcTemplate.query(sql, new Object[]{beerId}, reviewsMapper);

        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Couldn't find beer with id " + beerId);
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public BeerReview addBeerReview(BeerReview newReview) {
        String sql = "INSERT INTO review VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, newReview.getUserId(), newReview.getBeerId(),
                    newReview.getDescription());
            return newReview;
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public void unlinkBreweryBeer(int beerId) {
        String sql = "DELETE FROM brewery_beer WHERE beer_id = ?;";
        try {
            jdbcTemplate.update(sql, beerId);
        } catch (DataAccessException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public void deleteReviews(int beerId) {
        String sql = "DELETE FROM review WHERE beer_id = ?;";
        try {
            jdbcTemplate.update(sql, beerId);
        } catch (DataAccessException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public void deleteRatings(int beerId) {
        String sql = "DELETE FROM rating WHERE beer_id = ?;";
        try {
            jdbcTemplate.update(sql, beerId);
        } catch (DataAccessException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public void deleteBeer(int beerId) {
        String sql = "DELETE FROM beer WHERE beer_id = ?;";
        try {
            jdbcTemplate.update(sql, beerId);
        } catch (DataAccessException ex) {
            throw new DaoException(ex.getMessage());
        }
    }
}
