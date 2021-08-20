package com.epam.esm.controller;

import com.epam.esm.dto.TagDto;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {
    private TagService tagService;

    @Autowired
    public TagsController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping()
    public List<TagDto> findAll() {
        return tagService.findAll();
    }
}
