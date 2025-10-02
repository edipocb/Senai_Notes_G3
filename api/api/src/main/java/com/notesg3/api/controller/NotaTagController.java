package com.notesg3.api.controller;

import com.notesg3.api.model.NotaTag;
import com.notesg3.api.service.NotaTagService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes/notaTag")
@Tag(name = "NOTA_TAG", description = "Operações da Tabela NOTA_TAG")
public class NotaTagController {

    private final NotaTagService notaTagService;

    public NotaTagController(NotaTagService notaTagService) {
        this.notaTagService = notaTagService;
    }

    @GetMapping("/Tag/{idNota}")
    public ResponseEntity<List<NotaTag>> buscarTagPorNota(@PathVariable Integer idNota){
        List<NotaTag> listaTodasTagsPorNota = notaTagService.buscarTagPorNota(idNota);

        return ResponseEntity.ok(listaTodasTagsPorNota);
    }

    @GetMapping("/Nota/{idTag}")
    public ResponseEntity<List<NotaTag>> buscarNotaPorTag(@PathVariable Integer idTag){
        List<NotaTag> listaTodasNotasPorTag = notaTagService.buscarNotaPorTag(idTag);

        return ResponseEntity.ok(listaTodasNotasPorTag);
    }
}
