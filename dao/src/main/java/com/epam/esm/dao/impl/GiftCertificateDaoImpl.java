package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.rowmapper.GiftCertificateRowMapper;
import com.epam.esm.dao.rowmapper.TagRowMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final static String SELECT_ALL_GIFT_CERTIFICATES = "SELECT * FROM gift_certificates";
    private final static String SELECT_ONE_GIFT_CERTIFICATE = "SELECT * FROM gift_certificates WHERE id=?";
    private final static String SELECT_ONE_GIFT_CERTIFICATE_BY_NAME = "SELECT * FROM gift_certificates WHERE name=?";
    private final static String ADD_GIFT_CERTIFICATE = "INSERT INTO gift_certificates (name, description," +
            " price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String ADD_TAG_TO_GIFT_CERTIFICATE = "INSERT INTO tags_gift_certificates VALUES (?, ?)";
    private final static String CLEAR_GIFT_CERTIFICATE_TAGS = "DELETE FROM tags_gift_certificates WHERE gift_certificate_id=?";
    private final static String UPDATE_GIFT_CERTIFICATE = "UPDATE gift_certificates SET name = ?, description = ?, price = ?, duration = ?, last_update_date = ? WHERE id =?";
    private final static String DELETE_GIFT_CERTIFICATE = "DELETE FROM gift_certificates WHERE id=?";
    private final static String SELECT_TAGS = "SELECT * FROM tags INNER JOIN tags_gift_certificates " +
            "ON tags.id = tags_gift_certificates.tag_id WHERE tags_gift_certificates.gift_certificate_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(SELECT_ALL_GIFT_CERTIFICATES, new GiftCertificateRowMapper());
    }

    @Override
    public Optional<GiftCertificate> findOne(Long id) {
        return jdbcTemplate.query(SELECT_ONE_GIFT_CERTIFICATE, new GiftCertificateRowMapper(), id).
                stream().findAny();
    }

    @Override
    public Optional<GiftCertificate> findByName(String name) {
        return jdbcTemplate.query(SELECT_ONE_GIFT_CERTIFICATE_BY_NAME, new GiftCertificateRowMapper(), name).
                stream().findAny();
    }

    @Override
    public boolean add(GiftCertificate obj) {
        return jdbcTemplate.update(ADD_GIFT_CERTIFICATE, obj.getName(), obj.getDescription(),
                obj.getPrice(), obj.getDuration(), java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()),
                java.sql.Timestamp.valueOf(java.time.LocalDateTime.now())) == 1;
    }

    @Override
    public boolean update(GiftCertificate obj, Long id) {
        return jdbcTemplate.update(UPDATE_GIFT_CERTIFICATE, obj.getName(), obj.getDescription(), obj.getPrice(),
                obj.getDuration(), java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()), id) == 1;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(DELETE_GIFT_CERTIFICATE, id) == 1;
    }

    @Override
    public List<Tag> getTags(Long giftCertificateId) {
        return jdbcTemplate.query(SELECT_TAGS, new TagRowMapper(), giftCertificateId);

    }

    @Override
    public void clearTags(Long giftCertificateId) {
        jdbcTemplate.update(CLEAR_GIFT_CERTIFICATE_TAGS, giftCertificateId);
    }

    @Override
    public void addTag(Long tagId, Long giftCertificateId) {
        jdbcTemplate.update(ADD_TAG_TO_GIFT_CERTIFICATE, tagId, giftCertificateId);
    }
}
