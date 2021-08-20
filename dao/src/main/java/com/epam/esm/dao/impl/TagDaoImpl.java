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
    final static String SELECT_ALL_TAGS = "SELECT * FROM tags";


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
        return Optional.empty();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Tag insert(Tag obj) {
        return null;
    }

    @Override
    public Tag update(Tag obj, Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
