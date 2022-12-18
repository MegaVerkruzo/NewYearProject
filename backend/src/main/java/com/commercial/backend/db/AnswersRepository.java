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
        jdbcTemplate.update("INSERT INTO answers (word, day_of_month, post_link, description) VALUES (?, ?, ?, ?)",
                answer.getWord(), answer.getDayOfMonth(), answer.getPostLink(), answer.getDescription());
        logger.info("Paste answer word " + answer.getWord() + " in Database");
    }

    public Answer getAnswerByDay(Integer dayOfMonth) {
        List<Answer> result = jdbcTemplate.query("SELECT * FROM answers WHERE day_of_month = ?", mapper, dayOfMonth);

        logger.info("Get size of result" + result.size());

        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    private static class AnswerEntityMapper implements RowMapper<Answer> {

        @Override
        public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Answer(rs.getString("word"), rs.getInt("day_of_month"), rs.getString("post_link"), rs.getString("description"));
        }
    }
}
