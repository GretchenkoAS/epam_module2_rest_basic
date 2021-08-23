package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDto;

import java.util.List;

public interface GiftCertificateService {
    void add(GiftCertificateDto giftCertificateDto);

    GiftCertificateDto find(Long id);

    List<GiftCertificateDto> findAll();

    GiftCertificateDto findByName(String name);

    void delete(Long id);

    GiftCertificateDto update(GiftCertificateDto giftCertificateDto, Long id);

    boolean exist(GiftCertificateDto giftCertificateDto, Long id);
}
