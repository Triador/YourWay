package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.mapper.BookRowMapper;
import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.Profile;
import com.triador.yourwayserver.dao.model.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileDAOimpl implements ProfileDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProfileDAOimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(UserBook userBook) {
        String sql = "INSERT INTO users_books VALUES(?, ?, ?)";

        jdbcTemplate.update(sql,
                userBook.getUserId(),
                userBook.getBookId(),
                userBook.getBookStatus().name());
    }

    @Override
    public UserBook findUserBookRelationship(UserBook userBook) {
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

    @Override
    public Profile buildProfile(int userId) {
        String usersSql = "SELECT name, image_link FROM users WHERE user_id = ?";
        String booksSql = "SELECT * FROM books " +
                "INNER JOIN users_books " +
                "ON users_books.book_id = books.book_id " +
                "WHERE users_books.user_id = ?";

        Profile profile = jdbcTemplate.queryForObject(usersSql, new Object[]{userId}, (resultSet, i) -> {
            Profile tempProfile = new Profile();
            tempProfile.setUsername(resultSet.getString("name"));
            tempProfile.setImageLink(resultSet.getString("image_link"));
            return tempProfile;
        });

        List<Book> books = jdbcTemplate.query(booksSql, new Object[]{userId}, new BookRowMapper());

        List<Book> progressBooks = new ArrayList<>();
        List<Book> futureBooks = new ArrayList<>();
        List<Book> readedBooks = new ArrayList<>();

        if (profile != null) {
            for (Book book: books) {
                switch (book.getStatus()) {
                    case PROGRESS: {
                        progressBooks.add(book);
                        break;
                    }
                    case FUTURE: {
                        futureBooks.add(book);
                        break;
                    }
                    case FINISHED: {
                        readedBooks.add(book);
                    }
                }
            }

            profile.setProgressBooks(progressBooks);
            profile.setFutureBooks(futureBooks);
            profile.setFinishedBooks(readedBooks);
        }

        return profile;
    }

    @Override
    public int deleteBook(int userId, int bookId) {
        String sql = "DELETE FROM users_books " +
                "WHERE user_id = ? AND book_id = ?";

        return jdbcTemplate.update(sql, userId, bookId);
    }
}
