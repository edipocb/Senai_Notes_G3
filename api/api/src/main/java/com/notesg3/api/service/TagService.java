package com.notesg3.api.service;

import com.notesg3.api.repository.TagRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tag) {
        this.tagRepository = tag;
    }

    //Insert Into
    public Tag CadastrarTag(Tag tag){
        return tagRepository.save(tag);
    }

}
