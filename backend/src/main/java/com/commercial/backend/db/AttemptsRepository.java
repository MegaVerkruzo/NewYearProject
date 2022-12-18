package com.commercial.backend.db;

import com.commercial.backend.model.Attempt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AttemptsRepository {

    private final JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(AttemptsRepository.class);

    private static final AttemptEntityMapper mapper = new AttemptEntityMapper();

    public AttemptsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Attempt attempt) {
        jdbcTemplate.update("INSERT INTO attempts (id, phone, word, count_attempt, day_of_month) VALUES (?, ?, ?, ?, ?)",
                attempt.getCountAttempt(), attempt.getPhone(), attempt.getWord(), attempt.getCountAttempt(), attempt.getDayOfMonth());
        logger.info("Paste attempt with phone " + attempt.getPhone() + " and word " + attempt.getWord() + " in Database");
    }

    public List<Attempt> findAttemptsByPhoneAndDay(String phone, Integer dayOfMonth) {
        return jdbcTemplate.query("SELECT * FROM attempts WHERE phone = ? and day_of_month = ?", mapper, phone, dayOfMonth);
    }

    private static class AttemptEntityMapper implements RowMapper<Attempt> {

        @Override
        public Attempt mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Attempt(rs.getInt("id"), rs.getString("phone"), rs.getString("word"), rs.getInt("count_attempt"), rs.getInt("day_of_month"));
        }
    }
}