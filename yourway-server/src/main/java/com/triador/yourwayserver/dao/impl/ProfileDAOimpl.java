package com.triador.yourwayserver.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProfileDAOimpl implements ProfileDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProfileDAOimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(int userId, int bookId) {
        String sql = "INSERT INTO users_books VALUES(?, ?)";

        jdbcTemplate.update(sql, userId, bookId);
    }
}
