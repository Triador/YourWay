package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookTitle;
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
                "(russian_title, " +
                "origin_title, " +
                "author, " +
                "page_amount, " +
                "publication_year, " +
                "isbn, " +
                "description, " +
                "image_link) " +
                "VALUES ( " +
                ":russian_title, " +
                ":origin_title, " +
                ":autor, " +
                ":page_amount, " +
                ":publication_year, " +
                ":isbn, " +
                ":description, " +
                ":image_link) " +
                "ON CONFLICT " +
                "(russian_title) " +
                "DO NOTHING";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("russian_title", book.getRussianTitle())
                .addValue("origin_title", book.getOriginTitle())
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
    public int delete(int id) {
        String sql = "DELETE FROM books WHERE book_id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM books";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
    }

    @Override
    public Book findByRussianTitle(String russianTitle) {
        String sql = "SELECT * FROM books WHERE russian_title = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{russianTitle}, Book.class);
    }

    @Override
    public Book findById(int id) {
        String sql = "SELECT * FROM books WHERE book_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(Book.class));
    }

    @Override
    public List<BookTitle> findMatchByTitlePiece(String titlePiece) {
        String sql = "SELECT book_id, russian_title FROM books WHERE lower(russian_title) LIKE :piece";
        titlePiece = titlePiece.toLowerCase().trim() + "%";
        System.out.println(titlePiece);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("piece", titlePiece);

        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, (resultSet, i) -> {
            BookTitle bookTitle = new BookTitle();
            bookTitle.setId(resultSet.getInt("book_id"));
            bookTitle.setValue(resultSet.getString("russian_title"));
            return bookTitle;
        });
    }
}
