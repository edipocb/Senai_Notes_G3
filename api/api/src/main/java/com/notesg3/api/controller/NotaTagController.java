package com.notesg3.api.controller;

import com.notesg3.api.model.NotaTag;
import com.notesg3.api.service.NotaTagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes/notaTag")
public class NotaTagController {

    private final NotaTagService notaTagService;

    public NotaTagController(NotaTagService notaTagService) {
        this.notaTagService = notaTagService;
    }

    @GetMapping("/{idNota}")
    public ResponseEntity<List<NotaTag>> buscarNotaPorIdNota(@PathVariable Integer idNota){
        List<NotaTag> listaTodasAsTags = notaTagService.buscarTodasTags(idNota);

        return ResponseEntity.ok(listaTodasAsTags);
    }

}
