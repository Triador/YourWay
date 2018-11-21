package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users(" +
                "name, " +
                "password, " +
                "role) " +
                "VALUES(:name, :password, :role)";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("password", user.getPassword())
                .addValue("role", user.getRole());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[]{"id"});

        user.setUsersId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM users WHERE users_id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE users_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User findByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{name}, BeanPropertyRowMapper.newInstance(User.class));
    }
}
