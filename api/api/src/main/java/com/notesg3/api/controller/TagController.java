package com.notesg3.api.controller;

import com.notesg3.api.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes/tag")
@Tag(name = "TAG", description = "Operações da Tabela TAG.")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService)
    {
        this.tagService = tagService;
    }

    @PostMapping
    @Operation(
            summary = "Cadastro de Tag.",
            description = "Cadastro de Tag."
    )
    public ResponseEntity<Tag> cadastroTag(@RequestBody Tag tag)
    {
        return ResponseEntity.status(
                HttpStatus.CREATED).body(
                tagService.CadastrarTag(tag));
    }

}
