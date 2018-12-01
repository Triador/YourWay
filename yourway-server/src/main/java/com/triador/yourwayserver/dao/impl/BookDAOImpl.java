package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.mapper.BookRowMapper;
import com.triador.yourwayserver.dao.mapper.NoteRowMapper;
import com.triador.yourwayserver.dao.mapper.ShortBookDescriptionRowMapper;
import com.triador.yourwayserver.dao.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BookDAOImpl implements BookDAO {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BookDAOImpl(JdbcTemplate jdbcTemplate,
                       NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Book save(Book book) {
        String sql = "INSERT INTO books" +
                "(title, " +
                "author, " +
                "page_amount, " +
                "publication_year, " +
                "isbn, " +
                "description, " +
                "image_link) " +
                "VALUES ( " +
                ":title, " +
                ":autor, " +
                ":page_amount, " +
                ":publication_year, " +
                ":isbn, " +
                ":description, " +
                ":image_link) " +
                "ON CONFLICT " +
                "(title) " +
                "DO NOTHING";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("page_amount", book.getPageAmount())
                .addValue("publication_year", book.getPublicationYear())
                .addValue("isbn", book.getIsbn())
                .addValue("description", book.getDescription())
                .addValue("image_link", book.getImageLink());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[]{"book_id"});

        book.setBookId(keyHolder.getKey().intValue());
        return book;
    }

    @Override
    public List<ShortBookDescription> findAll() {
        String sql = "SELECT * FROM books";

        return jdbcTemplate.query(sql, new ShortBookDescriptionRowMapper());
    }

    @Override
    public Book findById(UserBook userBook) {
        String bookSql = "SELECT * FROM books WHERE book_id = ?";

        int bookId = userBook.getBookId();
        int userId = userBook.getUserId();

        Book book = (Book) jdbcTemplate.queryForObject(bookSql, new Object[]{bookId}, new BookRowMapper());

        if (book != null) {

            String userBookSql = "SELECT * FROM users_books WHERE book_id = ? AND user_id = ?";

            UserBook dbUserBook;
            try {
                dbUserBook = jdbcTemplate.queryForObject(userBookSql, new Object[]{bookId, userId},
                        BeanPropertyRowMapper.newInstance(UserBook.class));
                book.setStatus(dbUserBook.getBookStatus());
            } catch (EmptyResultDataAccessException ex) {
                book.setStatus(BookStatus.UNLOCK);
            }

            String noteSql = "SELECT * FROM notes WHERE book_id = ? AND user_id = ?";

            List<Note> notes = jdbcTemplate.query(noteSql, new Object[]{bookId, userId}, new NoteRowMapper());
            book.setNotes(notes);
        }

        return book;
    }

    @Override
    public List<BookTitle> findMatchByTitlePiece(String titlePiece) {
        String sql = "SELECT book_id, title FROM books WHERE lower(title) LIKE :piece";
        titlePiece = titlePiece.toLowerCase().trim() + "%";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("piece", titlePiece);

        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, (resultSet, i) -> {
            BookTitle bookTitle = new BookTitle();
            bookTitle.setId(resultSet.getInt("book_id"));
            bookTitle.setValue(resultSet.getString("title"));
            return bookTitle;
        });
    }
}
