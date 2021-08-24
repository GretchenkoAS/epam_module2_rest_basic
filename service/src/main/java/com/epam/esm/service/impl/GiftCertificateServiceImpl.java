package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.QueryDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.mapper.impl.QueryMapper;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final TagService tagService;
    private final GiftCertificateMapper mapper;
    private final TagMapper tagMapper;
    private final QueryMapper queryMapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagService tagService,
                                      GiftCertificateMapper mapper, TagMapper tagMapper, QueryMapper queryMapper) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagService = tagService;
        this.mapper = mapper;
        this.tagMapper = tagMapper;
        this.queryMapper = queryMapper;
    }

    @Transactional
    @Override
    public GiftCertificateDto add(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = mapper.mapDtoToEntity(giftCertificateDto);
        if (giftCertificateDao.findByName(giftCertificate.getName()).isPresent()) {
            //fixme add error
        }
        GiftCertificate giftCertificateInDb = giftCertificateDao.add(giftCertificate);
        addTags(giftCertificateInDb.getId(), giftCertificateDto.getTags());
        List<Tag> tagsInDb = giftCertificateDao.getTags(giftCertificateInDb.getId());
        giftCertificateInDb.setTags(tagsInDb);
        return mapper.mapEntityToDto(giftCertificateInDb);
    }

    @Transactional
    @Override
    public GiftCertificateDto find(Long id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findOne(id);
        if (giftCertificateOptional.isEmpty()) {
            //fixme add error
        }
        GiftCertificate giftCertificate = giftCertificateOptional.get();
        List<Tag> tags = giftCertificateDao.getTags(id);
        giftCertificate.setTags(tags);
        return mapper.mapEntityToDto(giftCertificate);
    }

    @Transactional
    @Override
    public List<GiftCertificateDto> findAll() {
        List<GiftCertificate> giftCertificates = giftCertificateDao.findAll();
        for (GiftCertificate giftCertificate : giftCertificates) {
            List<Tag> tags = giftCertificateDao.getTags(giftCertificate.getId());
            giftCertificate.setTags(tags);
        }
        return mapper.mapListEntityToListDto(giftCertificates);
    }

    @Transactional
    @Override
    public GiftCertificateDto findByName(String name) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findByName(name);
        if (giftCertificateOptional.isEmpty()) {
            //fixme add throw
        }
        GiftCertificate giftCertificate = giftCertificateOptional.get();
        List<Tag> tags = giftCertificateDao.getTags(giftCertificate.getId());
        giftCertificate.setTags(tags);
        return mapper.mapEntityToDto(giftCertificate);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        //fixme add throw
        giftCertificateDao.delete(id);
        giftCertificateDao.clearTags(id);
    }

    @Override
    public boolean exist(GiftCertificateDto giftCertificateDto, Long id) {
        Optional<GiftCertificate> certificateOptional = giftCertificateDao.findOne(id);
        if (certificateOptional.isEmpty()) {
            return false;
        }
        Optional<GiftCertificate> certificateByNameOptional = giftCertificateDao.findByName(giftCertificateDto.getName());
        if (certificateByNameOptional.isPresent() &&
                !certificateByNameOptional.get().getId().equals(giftCertificateDto.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public List<GiftCertificateDto> findByQuery(QueryDto queryDto) {
        List<GiftCertificate> giftCertificates = giftCertificateDao.findByQuery(queryMapper.mapDtoToEntity(queryDto));
        giftCertificates = giftCertificates.stream()
                .distinct().collect(Collectors.toList());
        for (GiftCertificate giftCertificate : giftCertificates) {
            List<Tag> tags = giftCertificateDao.getTags(giftCertificate.getId());
            giftCertificate.setTags(tags);
        }
        return mapper.mapListEntityToListDto(giftCertificates);
    }

    @Transactional
    @Override
    public GiftCertificateDto update(GiftCertificateDto giftCertificateDto, Long id) {
        if (exist(giftCertificateDto, id)) {
            //fixme add throw
        }
        giftCertificateDao.clearTags(id);
        GiftCertificate giftCertificateInDb = giftCertificateDao.update(mapper.mapDtoToEntity(giftCertificateDto), id);
        if (giftCertificateDto.getTags() != null) {
            addTags(id, giftCertificateDto.getTags());
            List<Tag> tagsInDb = giftCertificateDao.getTags(giftCertificateInDb.getId());
            giftCertificateInDb.setTags(tagsInDb);
        }
        return mapper.mapEntityToDto(giftCertificateInDb);
    }

    private void addTags(Long giftCertificateId, List<TagDto> tags) {
        if (tags != null && giftCertificateId != null) {
            List<TagDto> distinctTags = tags.stream().distinct().collect(Collectors.toList());
            for (TagDto tagDto : distinctTags) {
                if (!tagService.exist(tagDto)) {
                    tagService.add(tagDto);
                }
                giftCertificateDao.addTag(tagMapper.mapDtoToEntity(tagService.findByName(tagDto.getName())).getId(),
                        giftCertificateId);
            }
        }
    }
}
