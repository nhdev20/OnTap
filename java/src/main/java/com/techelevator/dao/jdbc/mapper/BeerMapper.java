package com.techelevator.dao.jdbc.mapper;

import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeerMapper implements RowMapper<Beer> {
    @Override
    public Beer mapRow(ResultSet resultSet, int i) throws SQLException {
        Beer beer = new Beer();
        beer.setId(resultSet.getInt("beer_id"));
        beer.setName(resultSet.getString("beer_name"));
        beer.setDescription(resultSet.getString("description"));
        beer.setAbv(resultSet.getDouble("abv"));
        beer.setType(resultSet.getString("beer_type"));
        return beer;
    }
}
