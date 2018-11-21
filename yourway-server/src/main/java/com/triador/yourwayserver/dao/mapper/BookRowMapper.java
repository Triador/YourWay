package com.triador.yourwayserver.dao.mapper;

import com.triador.yourwayserver.dao.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getInt("book_id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setDescription(resultSet.getString("description"));
        book.setPageAmount(resultSet.getInt("page_amount"));
        book.setPublicationYear(resultSet.getInt("publication_year"));
        book.setImageLink(resultSet.getString("image_link"));
        return book;
    }
}
