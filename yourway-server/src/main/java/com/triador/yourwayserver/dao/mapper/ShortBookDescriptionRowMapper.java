package com.triador.yourwayserver.dao.mapper;

import com.triador.yourwayserver.dto.response.ShortBookDescriptionResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShortBookDescriptionRowMapper implements RowMapper {
    @Nullable
    @Override
    public ShortBookDescriptionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShortBookDescriptionResponse shortBookDescription = new ShortBookDescriptionResponse();
        shortBookDescription.setBookId(rs.getInt("book_id"));
        shortBookDescription.setImageLink(rs.getString("image_link"));
        shortBookDescription.setTitle(rs.getString("title"));
        return shortBookDescription;
    }
}
