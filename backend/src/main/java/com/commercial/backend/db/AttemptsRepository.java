package com.commercial.backend.db;

import com.commercial.backend.db.entities.Attempt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        attempt.setSize(attempt.getSize() + 1);
        jdbcTemplate.update("INSERT INTO attempts (id, phone, word, date) VALUES (?, ?, ?, ?)",
                attempt.getSize(), attempt.getPhone(), attempt.getWord(), attempt.getDate());
        logger.info("Paste attempt with phone " + attempt.getPhone() + " and word " + attempt.getWord() + " in Database");
    }

    public List<Attempt> findAllByPhone(String phone) {
        logger.info("findAllByPhone");
        return jdbcTemplate.query("SELECT * FROM attempts WHERE phone = ? ORDER BY date", mapper, phone);
    }

    private static class AttemptEntityMapper implements RowMapper<Attempt> {

        @Override
        public Attempt mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Attempt(rs.getString("phone"), rs.getString("word"), Timestamp.valueOf(rs.getTimestamp("date").toLocalDateTime()));
        }
    }
}
