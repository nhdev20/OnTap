package com.techelevator.dao.jdbc.mapper;

import com.techelevator.model.DayHours;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DayHoursMapper implements RowMapper<DayHours> {
    @Override
    public DayHours mapRow(ResultSet resultSet, int i) throws SQLException {
        DayHours curDayHours = new DayHours();
        curDayHours.setDay(resultSet.getString("day_name"));
        curDayHours.setOpenStatus(resultSet.getBoolean("open_status"));
        curDayHours.setStartTime(resultSet.getInt("start_time"));
        curDayHours.setStartAmPm(resultSet.getString("start_am_pm"));
        curDayHours.setEndTime(resultSet.getInt("end_time"));
        curDayHours.setEndAmPm(resultSet.getString("end_am_pm"));
        return curDayHours;
    }

}
