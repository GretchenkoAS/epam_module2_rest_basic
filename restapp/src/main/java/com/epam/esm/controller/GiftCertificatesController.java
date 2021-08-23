package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/giftCertificates")
public class GiftCertificatesController {
    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificatesController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public List<GiftCertificateDto> findAll() {
        return giftCertificateService.findAll();
    }

    @GetMapping("/{id}")
    public GiftCertificateDto find(@PathVariable Long id) {
        return giftCertificateService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody GiftCertificateDto newGiftCertificate) {
        giftCertificateService.add(newGiftCertificate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        giftCertificateService.delete(id);
    }

    @PutMapping("/{id}")
    public GiftCertificateDto update(@RequestBody GiftCertificateDto updatedCertificateDTO, @PathVariable Long id){
        return giftCertificateService.update(updatedCertificateDTO, id);
    }

    @GetMapping("/testAdd")
    public void testAdd() {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setName("testName");
        giftCertificateDto.setDescription("test");
        giftCertificateDto.setPrice(BigDecimal.TEN);
        giftCertificateDto.setDuration(10);
        List<TagDto> tagDtos = new ArrayList<>();
        TagDto tagDto = new TagDto();
        tagDto.setName("6666");
        tagDtos.add(tagDto);
        giftCertificateDto.setTags(tagDtos);
        giftCertificateService.add(giftCertificateDto);
    }
}
