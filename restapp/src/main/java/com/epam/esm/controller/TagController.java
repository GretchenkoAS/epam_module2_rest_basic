package com.epam.esm.controller;

import com.epam.esm.dto.TagDto;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDto> findAll() {
        return tagService.findAll();
    }

    @GetMapping("/{id}")
    public TagDto find(@PathVariable Long id) {
        return tagService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto add(@RequestBody TagDto newTag) {
        return tagService.add(newTag);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        tagService.delete(id);
    }
}
