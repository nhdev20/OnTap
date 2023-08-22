package com.techelevator.dao.jdbc.mapper;

import com.techelevator.model.Brewery;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BreweryMapper implements RowMapper<Brewery> {
    @Override
    public Brewery mapRow(ResultSet resultSet, int i) throws SQLException  {
        Brewery brewery = new Brewery();
        brewery.setId(resultSet.getInt("brewery_id"));
        brewery.setBrewerId(resultSet.getInt("brewer"));
        brewery.setOpenDbId(resultSet.getString("open_db_id"));
        brewery.setName(resultSet.getString("brewery_name"));;
        brewery.setBreweryType(resultSet.getString("brewery_type"));
        brewery.setPhoneNumber(resultSet.getString("phone_number"));
        brewery.setWebsite(resultSet.getString("website"));
        brewery.setStreetAddress1(resultSet.getString("street_address_1"));
        brewery.setStreetAddress2(resultSet.getString("street_address_2"));
        brewery.setCity(resultSet.getString("city"));
        brewery.setStateProvince(resultSet.getString("state_province"));
        brewery.setPostalCode(resultSet.getString("postal_code"));
        brewery.setLatitude(resultSet.getDouble("latitude"));
        brewery.setLongitude(resultSet.getDouble("longitude"));
        brewery.setCountry(resultSet.getString("country"));
        brewery.setHistory(resultSet.getString("history"));
        return brewery;
    }
}
