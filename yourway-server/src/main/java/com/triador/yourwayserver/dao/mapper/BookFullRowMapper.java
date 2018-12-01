package com.triador.yourwayserver.dao.mapper;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookFullRowMapper implements RowMapper {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getInt("book_id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setDescription(resultSet.getString("description"));
        book.setPageAmount(resultSet.getInt("page_amount"));
        book.setPublicationYear(resultSet.getInt("publication_year"));
        book.setImageLink(resultSet.getString("image_link"));

        int userId = resultSet.getInt("user_id");
        if (userId != 0) {
            book.setDisable(true);

            String status = resultSet.getString("status");
            book.setStatus(BookStatus.valueOf(status));
        } else {
            book.setStatus(BookStatus.UNLOCK);
        }

        return book;
    }
}
