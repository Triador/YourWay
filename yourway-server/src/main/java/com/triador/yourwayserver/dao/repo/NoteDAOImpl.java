package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class NoteDAOImpl implements NoteRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public NoteDAOImpl(JdbcTemplate jdbcTemplate,
                       NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Note save(Note note) {
        String sql = "INSERT INTO note(book_id, user_id, text) VALUES(:book_id, :user_id, :text)";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("book_id", note.getBookId())
                .addValue("user_id", note.getUserId())
                .addValue("text", note.getText());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[]{"note_id"});

        note.setId(keyHolder.getKey().intValue());

        return note;
    }

    @Override
    public int delete(int noteId, int bookId) {
        String sql = "DELETE FROM note WHERE id = ? AND book_id = ?";

        return jdbcTemplate.update(sql, noteId, bookId);
    }
}
