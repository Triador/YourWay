package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    public int save(Book book) {
        String sql = "INSERT INTO books" +
                "(russian_title, " +
                "origin_title, " +
                "author, " +
                "page_amount, " +
                "publication_year, " +
                "isbn, " +
                "description, " +
                "image_link) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT " +
                "(russian_title) " +
                "DO NOTHING";

        return jdbcTemplate.update(sql,
                book.getRussianTitle(),
                book.getOriginTitle(),
                book.getAuthor(),
                book.getPageAmount(),
                book.getPublicationYear(),
                book.getIsbn(),
                book.getDescription(),
                book.getImageLink());
    }

    @Override
    public int delete(Book book) {
        String sql = "DELETE FROM books WHERE id = ?";

        return jdbcTemplate.update(sql, book.getId());
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
        String sql = "SELECT * FROM books WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(Book.class));
    }

    @Override
    public List<BookTitle> findMatchByTitlePiece(String titlePiece) {
        String sql = "SELECT id, russian_title FROM books WHERE lower(russian_title) LIKE :piece";
        titlePiece = titlePiece.toLowerCase().trim() + "%";
        System.out.println(titlePiece);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("piece", titlePiece);

        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, (resultSet, i) -> {
            BookTitle bookTitle = new BookTitle();
            bookTitle.setId(resultSet.getInt("id"));
            bookTitle.setValue(resultSet.getString("russian_title"));
            return bookTitle;
        });
    }
}
