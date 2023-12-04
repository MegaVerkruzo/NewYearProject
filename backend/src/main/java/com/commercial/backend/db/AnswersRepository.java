package com.commercial.backend.db;

import com.commercial.backend.db.entities.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AnswersRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final AnswerEntityMapper mapper = new AnswerEntityMapper();

    public AnswersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Answer> findAll() {
        return jdbcTemplate.query("SELECT * FROM answers ORDER BY date", mapper);
    }

    private static class AnswerEntityMapper implements RowMapper<Answer> {

        @Override
        public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Answer(rs.getString("word"), rs.getTimestamp("date"), rs.getString("description"));
        }
    }
}
