package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.mapper.BookRowMapper;
import com.triador.yourwayserver.dao.mapper.NoteRowMapper;
import com.triador.yourwayserver.dao.mapper.ShortBookDescriptionRowMapper;
import com.triador.yourwayserver.dao.model.Note;
import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dto.request.AddUserBookRequest;
import com.triador.yourwayserver.dto.response.BookTitleResponse;
import com.triador.yourwayserver.dto.response.ShortBookDescriptionResponse;
import com.triador.yourwayserver.enumeration.BookStatus;
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

import java.util.List;

@Component
public class BookDAOImpl implements BookRepository {

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
        String sql = "INSERT INTO book" +
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

        book.setId(keyHolder.getKey().intValue());
        return book;
    }

    @Override
    public List<ShortBookDescriptionResponse> findAll() {
        String sql = "SELECT * FROM book";

        return jdbcTemplate.query(sql, new ShortBookDescriptionRowMapper());
    }

    @Override
    public Book findById(AddUserBookRequest userBook) {
        String bookSql = "SELECT * FROM book WHERE id = ?";

        int bookId = userBook.getBookId();
        int userId = userBook.getUserId();

        Book book = (Book) jdbcTemplate.queryForObject(bookSql, new Object[]{bookId}, new BookRowMapper());

        if (book != null) {

            String userBookSql = "SELECT * FROM user_book WHERE book_id = ? AND user_id = ?";

            AddUserBookRequest dbUserBook;
            try {
                dbUserBook = jdbcTemplate.queryForObject(userBookSql, new Object[]{bookId, userId},
                        BeanPropertyRowMapper.newInstance(AddUserBookRequest.class));
                book.setStatus(dbUserBook.getBookStatus());
            } catch (EmptyResultDataAccessException ex) {
                book.setStatus(BookStatus.UNLOCK);
            }

            String noteSql = "SELECT * FROM note WHERE book_id = ? AND user_id = ?";

            List<Note> notes = jdbcTemplate.query(noteSql, new Object[]{bookId, userId}, new NoteRowMapper());
            book.setNotes(notes);
        }

        return book;
    }

    @Override
    public List<BookTitleResponse> findByTitleContaining(String titlePiece) {
        String sql = "SELECT id, title FROM book WHERE lower(title) LIKE :piece";
        titlePiece = titlePiece.toLowerCase().trim() + "%";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("piece", titlePiece);

        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, (resultSet, i) -> {
            BookTitleResponse bookTitle = new BookTitleResponse();
            bookTitle.setId(resultSet.getInt("book_id"));
            bookTitle.setValue(resultSet.getString("title"));
            return bookTitle;
        });
    }
}
