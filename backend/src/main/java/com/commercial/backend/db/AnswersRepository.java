package com.commercial.backend.db;

import com.commercial.backend.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AnswersRepository {
    private final JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(AnswersRepository.class);

    private static final AnswerEntityMapper mapper = new AnswerEntityMapper();

    public AnswersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Answer answer) {
        answer.setSize(answer.getSize() + 1);
        jdbcTemplate.update("INSERT INTO answers (id, word, date, description) VALUES (?, ?, ?, ?)",
                answer.getSize(), answer.getWord(), answer.getDate(), answer.getDescription());
        logger.info("Paste answer word " + answer.getWord() + " in Database");
    }

    public List<Answer> findAll() {
        return jdbcTemplate.query("SELECT * FROM answers ORDER BY date ASC", mapper);
    }

    private static class AnswerEntityMapper implements RowMapper<Answer> {

        @Override
        public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Answer(rs.getString("word"), rs.getTimestamp("date"), rs.getString("description"));
        }
    }
}
