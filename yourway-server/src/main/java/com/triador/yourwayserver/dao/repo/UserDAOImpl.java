package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.User;
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
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO "user"(" +
                "name, " +
                "password, " +
                "role, " +
                "image_link) " +
                "VALUES(" +
                ":name, " +
                ":password, " +
                ":role, " +
                ":image_link)";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("password", user.getPassword())
                .addValue("role", user.getRole())
                .addValue("image_link", user.getImageLink());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[]{"user_id"});

        user.setId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public int delete(int userId) {
        String sql = "DELETE FROM "user" WHERE id = ?";

        return jdbcTemplate.update(sql, userId);
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM "user"";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User findById(int userId) {
        String sql = "SELECT * FROM "user" WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User findByName(String name) {
        String sql = "SELECT * FROM "user" WHERE name = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{name}, BeanPropertyRowMapper.newInstance(User.class));
    }
}
