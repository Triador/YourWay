package com.triador.yourwayserver.dao.mapper;

import com.triador.yourwayserver.dao.model.Note;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteRowMapper implements RowMapper {
    @Nullable
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Note note = new Note();
        note.setId(rs.getInt("note_id"));
        note.setBookId(rs.getInt("book_id"));
        note.setUserId(rs.getInt("user_id"));
        note.setText(rs.getString("text"));
        return note;
    }
}
