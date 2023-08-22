package com.techelevator.dao.jdbc;

import com.techelevator.dao.BreweryDao;
import com.techelevator.dao.jdbc.mapper.BreweryMapper;
import com.techelevator.exception.ResourceNotFoundException;
import com.techelevator.model.Brewery;
import com.techelevator.exception.DaoException;
import com.techelevator.model.DayHours;
import com.techelevator.openbrewerydb.model.OpenBreweryDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBreweryDao implements BreweryDao {

    private final JdbcTemplate jdbcTemplate;

    private final BreweryMapper breweryMapper = new BreweryMapper();

    public JdbcBreweryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Brewery> getBreweries() {
        String sql = "SELECT * FROM brewery";
        try {
            return jdbcTemplate.query(sql, breweryMapper);
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }

    }

    public Brewery getBreweryById(Integer id) throws ResourceNotFoundException {
        String sql = "SELECT * FROM brewery WHERE brewery_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, breweryMapper);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Couldn't find brewery with id " + id);
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public List<DayHours> getBreweryHours(int breweryId) {
        List<DayHours> dayHours = new ArrayList<>();
        String sql = "SELECT bd.day_name, bd.open_status, bd.start_time, bd.start_am_pm, bd.end_time, bd.end_am_pm FROM brewery b\n" +
                "JOIN brewery_days bd ON b.brewery_id = bd.brewery_id\n" +
                "JOIN days d ON bd.day_name = d.day_name\n" +
                "WHERE b.brewery_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);
            if (results  != null) {
                while (results.next()) {
                    DayHours curDayHours = new DayHours();
                    curDayHours.setDay(results.getString("day_name"));
                    curDayHours.setOpenStatus(results.getBoolean("open_status"));
                    curDayHours.setStartTime(results.getInt("start_time"));
                    curDayHours.setStartAmPm(results.getString("start_am_pm"));
                    curDayHours.setEndTime(results.getInt("end_time"));
                    curDayHours.setEndAmPm(results.getString("end_am_pm"));
                    dayHours.add(curDayHours);
                }
            }
        } catch (DataAccessException e) {
            throw new DaoException("Connection issues.");
        }
        return dayHours;
    }

    public Brewery getBreweryByOpenDbId(String openDbId) {
        String sql = "SELECT * FROM brewery WHERE open_db_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{openDbId}, breweryMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }


    public void addBreweryFromOpenDb(OpenBreweryDTO brewery) {
        String sql = "INSERT INTO brewery (brewery_id, open_db_id, brewery_name, brewery_type, phone_number, website, street_address_1, " +
                "street_address_2, city, state_province, postal_code, latitude, longitude, country) " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Double latitude = null;
        Double longitude = null;
        try {
            if (brewery.getLatitude() != null && brewery.getLongitude() != null) {
                latitude = Double.parseDouble(brewery.getLatitude());
                longitude = Double.parseDouble(brewery.getLongitude());
            }

            jdbcTemplate.update(sql, brewery.getId(),
                    brewery.getName(), brewery.getBreweryType(), brewery.getPhone(), brewery.getWebsiteUrl(),
                    brewery.getAddress1(), brewery.getAddress2(), brewery.getCity(), brewery.getStateProvince(),
                    brewery.getPostalCode(), latitude, longitude, brewery.getCountry());
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }


    public void updateBrewery(Brewery brewery, Integer id) {
        String sql = "UPDATE brewery SET brewer = ?, brewery_name = ?, brewery_type = ?, phone_number = ?, website = ?, street_address_1 = ?, " +
                "street_address_2 = ?, city = ?, state_province = ?, postal_code = ?, country = ?, latitude = ?, longitude = ?, history = ?" +
                " WHERE brewery_id = ?";
        try {
            jdbcTemplate.update(sql, brewery.getBrewerId(), brewery.getName(),
                    brewery.getBreweryType(), brewery.getPhoneNumber(), brewery.getWebsite(),
                    brewery.getStreetAddress1(), brewery.getStreetAddress2(), brewery.getCity(), brewery.getStateProvince(),
                    brewery.getPostalCode(), brewery.getCountry(), brewery.getLatitude(), brewery.getLongitude(), brewery.getHistory(),
                    brewery.getId());
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }

    }

    public void updateBreweryDays(int breweryId, DayHours day) {
        String sql = "UPDATE brewery_days SET open_status = ?, start_time = ?, start_am_pm = ?, end_time = ?, end_am_pm = ?" +
                "WHERE brewery_id = ? AND day_name = ?";
        try {
            jdbcTemplate.update(sql, day.isOpenStatus(), day.getStartTime(), day.getStartAmPm(), day.getEndTime(),
                    day.getEndAmPm(), breweryId, day.getDay());
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
    }

}
