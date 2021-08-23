package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.rowmapper.TagRowMapper;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(SELECT_ALL_TAGS, new TagRowMapper());
    }

    @Override
    public Optional<Tag> findOne(Long id) {
        return jdbcTemplate.query(SELECT_ONE_TAG, new TagRowMapper(), id).
                stream().findAny();
    }

    @Override
    public boolean add(Tag obj) {
        return jdbcTemplate.update(ADD_TAG, obj.getName()) == 1;
    }

    @Override
    public boolean update(Tag obj, Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(DELETE_TAG, id) == 1;
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return jdbcTemplate.query(SELECT_ONE_TAG_BY_NAME, new TagRowMapper(), name).
                stream().findAny();
    }
}
