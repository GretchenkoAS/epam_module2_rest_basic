package com.epam.esm.controller;

import com.epam.esm.dto.TagDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {
    @GetMapping()
    public List<TagDTO> findAll() {
        List<TagDTO> tagList = new ArrayList<>();
        TagDTO tag = new TagDTO();
        tag.setName("AAA");
        tag.setId(1L);
        tagList.add(tag);
        System.out.println(tagList);
        return tagList;
    }
}
