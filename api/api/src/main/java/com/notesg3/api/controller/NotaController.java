package com.notesg3.api.controller;

import com.notesg3.api.model.Nota;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.notesg3.api.service.NotaService;

import java.util.List;

@RestController
@RequestMapping("/notes/nota")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping("/email/{emailUser}")
    public ResponseEntity<List<Nota>> buscarNotaPorEmailUsuario(@PathVariable String emailUser) {
        List<Nota> listaTodasNotas = notaService.buscarNotaPorEmailUsuario(emailUser);
        return ResponseEntity.ok(listaTodasNotas);
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Nota>> buscarNotaPorTagName(@PathVariable String tag) {
        List<Nota> listaPorTag = notaService.buscarNotaPorTagName(tag);
        return ResponseEntity.ok(listaPorTag);
    }

    //@GetMapping("/idEEmail/{idNota}{email}")

    @PostMapping
    public ResponseEntity<Nota> cadastrarNota(@RequestBody Nota nota) {
        notaService.cadastroNota(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nota);
    }
}
