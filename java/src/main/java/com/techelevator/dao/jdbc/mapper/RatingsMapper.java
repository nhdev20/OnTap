package com.techelevator.dao.jdbc.mapper;

import com.techelevator.model.BeerRating;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingsMapper implements RowMapper<BeerRating> {

    @Override
    public BeerRating mapRow(ResultSet resultSet, int i) throws SQLException {
        BeerRating curRating = new BeerRating();
        curRating.setUserId(resultSet.getInt("user_id"));
        curRating.setBeerId(resultSet.getInt("beer_id"));
        curRating.setAmount(resultSet.getInt("amount"));
        return curRating;
    }
}
