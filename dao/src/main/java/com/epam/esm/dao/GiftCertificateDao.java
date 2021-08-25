package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Query;
import com.epam.esm.entity.Tag;

import java.util.List;

/**
 * DAO interface responsible for processing CRUD operations for gift certificates
 *
 * @author Andrey Gretchenko
 * @see BaseDao
 */
public interface GiftCertificateDao extends BaseDao<GiftCertificate> {
    /**
     * Returns List of tags for gift certificate.
     *
     * @param giftCertificateId id of gift certificate
     * @return List of tags
     */
    List<Tag> getTags(Long giftCertificateId);

    /**
     * Adds a tag to the certificate.
     *
     * @param tagId             id of tag
     * @param giftCertificateId id of gift certificate
     */
    void addTag(Long tagId, Long giftCertificateId);

    /**
     * Removes tags from the certificate.
     *
     * @param id id of gift certificate
     */
    void clearTags(Long id);

    /**
     * Retrieves gift certificates from repository according to provided query.
     *
     * @param query object for building search query
     * @return List<GiftCertificate> list of gift certificates from repository according to provided query
     */
    List<GiftCertificate> findByQuery(Query query);
}
