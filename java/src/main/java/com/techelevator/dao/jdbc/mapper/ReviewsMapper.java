package com.techelevator.dao.jdbc.mapper;

import com.techelevator.model.BeerRating;
import com.techelevator.model.BeerReview;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewsMapper implements RowMapper<BeerReview> {

    @Override
    public BeerReview mapRow(ResultSet resultSet, int i) throws SQLException {
        BeerReview curReview = new BeerReview();
        curReview.setUserId(resultSet.getInt("user_id"));
        curReview.setBeerId(resultSet.getInt("beer_id"));
        curReview.setDescription(resultSet.getString("description"));
        return curReview;
    }
}
