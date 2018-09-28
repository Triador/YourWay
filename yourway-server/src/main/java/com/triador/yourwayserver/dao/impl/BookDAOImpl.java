package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDAOImpl implements BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveBook(Book book) {
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

        jdbcTemplate.update(sql,
                book.getRussianTitle(),
                book.getOriginTitle(),
                book.getAuthor(),
                book.getPageAmount(),
                book.getPublicationYear(),
                book.getIsbn(),
                book.getDescription(),
                book.getImageLink());
    }
}
