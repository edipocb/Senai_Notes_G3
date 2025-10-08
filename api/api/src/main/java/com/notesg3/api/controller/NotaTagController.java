package com.notesg3.api.controller;

import com.notesg3.api.dto.NotaTagDTO.CadastroNotaTagDTO;
import com.notesg3.api.model.Nota;
import com.notesg3.api.model.NotaTag;
import com.notesg3.api.service.NotaTagService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes/notaTag")
@Tag(name = "NOTA_TAG", description = "Operações da Tabela NOTA_TAG")
@SecurityRequirement(name = "bearerAuth")
public class NotaTagController {

    private final NotaTagService notaTagService;

    public NotaTagController(NotaTagService notaTagService) {
        this.notaTagService = notaTagService;
    }

//    @GetMapping("/Tag/{idNota}")
//    public ResponseEntity<List<NotaTag>> buscarTagPorNota(@PathVariable Integer idNota){
//        List<NotaTag> listaTodasTagsPorNota = notaTagService.buscarTagPorNota(idNota);
//
//        return ResponseEntity.ok(listaTodasTagsPorNota);
//    }
//
//    @GetMapping("/Nota/{idTag}")
//    public ResponseEntity<List<NotaTag>> buscarNotaPorTag(@PathVariable Integer idTag){
//        List<NotaTag> listaTodasNotasPorTag = notaTagService.buscarNotaPorTag(idTag);
//
//        return ResponseEntity.ok(listaTodasNotasPorTag);
//    }

    @PostMapping
    public ResponseEntity<NotaTag> cadastrarNotaTag(@RequestBody CadastroNotaTagDTO dto) {
        NotaTag notaSalvo = notaTagService.cadastrarNotaTag(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(notaSalvo);
    }
}
