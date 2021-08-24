package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.rowmapper.TagRowMapper;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao {
    private final static String SELECT_ALL_TAGS = "SELECT * FROM tags";
    private final static String SELECT_ONE_TAG = "SELECT * FROM tags WHERE id=?";
    private final static String ADD_TAG = "INSERT INTO tags (name) VALUES (?)";
    private final static String DELETE_TAG = "DELETE FROM tags WHERE id=?";
    private final static String SELECT_ONE_TAG_BY_NAME = "SELECT * FROM tags WHERE name=?";

    private final JdbcTemplate jdbcTemplate;
    private final TagRowMapper tagRowMapper;


    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate, TagRowMapper tagRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagRowMapper = tagRowMapper;

    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(SELECT_ALL_TAGS, tagRowMapper);
    }

    @Override
    public Optional<Tag> findOne(Long id) {
        return jdbcTemplate.query(SELECT_ONE_TAG, tagRowMapper, id)
                .stream()
                .findAny();
    }

    @Override
    public Tag add(Tag obj) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(ADD_TAG, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getName());
            return ps;
        }, keyHolder);
        obj.setId(keyHolder.getKey().longValue());
        return obj;
    }


    @Override
    public Tag update(Tag obj, Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_TAG, id);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return jdbcTemplate.query(SELECT_ONE_TAG_BY_NAME, tagRowMapper, name)
                .stream()
                .findAny();
    }
}
