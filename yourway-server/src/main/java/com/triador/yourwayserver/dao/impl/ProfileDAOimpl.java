package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
    public void save(UserBook userBook) {
        String sql = "INSERT INTO users_books VALUES(?, ?)";

        jdbcTemplate.update(sql,
                userBook.getUserId(),
                userBook.getBookId());
    }

    @Override
    public UserBook findByIds(UserBook userBook) {
        String sql = "SELECT * FROM users_books WHERE user_id = ? AND book_id = ?";

        int userId = userBook.getUserId();
        int bookId = userBook.getBookId();

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId, bookId},
                    BeanPropertyRowMapper.newInstance(UserBook.class));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
