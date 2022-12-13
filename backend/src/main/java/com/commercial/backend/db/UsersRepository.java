package com.commercial.backend.db;

import com.commercial.backend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsersRepository {

    private final JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(UsersRepository.class);

    private static final IdeaEntityMapper mapper = new IdeaEntityMapper();

    public UsersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(User user) {
        jdbcTemplate.update("INSERT INTO users (phone, name, surname, middle_name, email, place, password_hash, token) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                user.getPhone(), user.getName(), user.getSurname(), user.getMiddleName(), user.getEmail(), user.getPlace(), user.getPasswordHash(), user.getToken());
        logger.info("Paste user with phone " + user.getPhone() + " in Database");
    }

    public User findUserByPhone(String phone) {
        List<User> result = jdbcTemplate.query("SELECT * FROM users WHERE phone = ?", mapper, phone);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public User findUserByToken(String token) {
        List<User> result = jdbcTemplate.query("SELECT * FROM users WHERE token = ?", mapper, token);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    private static class IdeaEntityMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getString("phone"), rs.getString("name"), rs.getString("surname"), rs.getString("middle_name"), rs.getString("email"), rs.getString("place"), rs.getString("password_hash"), true);
        }
    }
}
