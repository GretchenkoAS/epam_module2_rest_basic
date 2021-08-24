package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Query;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface GiftCertificateDao extends BaseDao<GiftCertificate> {
    List<Tag> getTags(Long giftCertificateId);
    void addTag(Long tagId, Long giftCertificateId);
    void clearTags(Long id);
    List<GiftCertificate> findByQuery(Query query);
}
