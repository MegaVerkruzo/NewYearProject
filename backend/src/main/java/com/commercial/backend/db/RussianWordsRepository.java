package com.commercial.backend.db;

import com.commercial.backend.model.RussianWord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RussianWordsRepository {
    private final JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(RussianWordsRepository.class);

    private static final RussianWordEntityMapper mapper = new RussianWordEntityMapper();

    public RussianWordsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isRussianWord(String word) {
        logger.info("Check if word " + word + " is russian");
        return jdbcTemplate.query("SELECT * FROM russian_words WHERE word = ?", mapper, word).size() > 0;
    }

    private static class RussianWordEntityMapper implements RowMapper<RussianWord> {

        @Override
        public RussianWord mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new RussianWord(rs.getLong("id"), rs.getString("word"));
        }
    }
}
