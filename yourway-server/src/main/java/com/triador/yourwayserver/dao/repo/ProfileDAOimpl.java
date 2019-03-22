package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dto.response.ProfileResponse;
import com.triador.yourwayserver.dto.request.AddUserBookRequest;
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
    public void save(AddUserBookRequest userBook) {
        String sql = "INSERT INTO user_book VALUES(?, ?, ?)";

        jdbcTemplate.update(sql,
                userBook.getUserId(),
                userBook.getBookId(),
                userBook.getBookStatus().name());
    }

    @Override
    public AddUserBookRequest findUserBookRelationship(AddUserBookRequest userBook) {
        String sql = "SELECT * FROM user_book WHERE user_id = ? AND book_id = ?";

        int userId = userBook.getUserId();
        int bookId = userBook.getBookId();

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId, bookId},
                    BeanPropertyRowMapper.newInstance(AddUserBookRequest.class));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public ProfileResponse buildProfile(int userId) {
        String usersSql = "SELECT name, image_link FROM "user" WHERE id = ?";
        String booksSql = "SELECT * FROM book " +
                "INNER JOIN user_book " +
                "ON user_book.book_id = book.id " +
                "WHERE user_book.user_id = ?";

        ProfileResponse profile = jdbcTemplate.queryForObject(usersSql, new Object[]{userId}, (resultSet, i) -> {
            ProfileResponse tempProfile = new ProfileResponse();
            tempProfile.setUsername(resultSet.getString("name"));
            tempProfile.setImageLink(resultSet.getString("image_link"));
            return tempProfile;
        });

        List<Book> books = jdbcTemplate.query(booksSql, new Object[]{userId}, new BookFullRowMapper());

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
        String sql = "DELETE FROM user_book " +
                "WHERE user_id = ? AND book_id = ?";

        return jdbcTemplate.update(sql, userId, bookId);
    }
}
