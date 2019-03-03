package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Marathon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarathonDAOImpl implements MarathonDAO {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public MarathonDAOImpl(JdbcTemplate jdbcTemplate,
                           NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Marathon saveMarathon(Marathon marathon) {
        String sql = "INSERT INTO marathons(start_date, end_date, description)" +
                " VALUES(:startDate, :endDate, :description)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("startDate", marathon.getStartDate())
                .addValue("endDate", marathon.getEndDate())
                .addValue("description", marathon.getDescription());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource, keyHolder, new String[]{"marathon_id"});

        marathon.setMarathonId(keyHolder.getKey().intValue());
        return marathon;
    }

    @Override
    public Marathon getMarathon(int marathonId) {
        String sql = "SELECT * FROM marathons WHERE marathon_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{marathonId}, BeanPropertyRowMapper.newInstance(Marathon.class));
    }

    @Override
    public List<Marathon> getMarathons() {
        String sql = "SELECT * FROM marathons";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Marathon.class));
    }
}
