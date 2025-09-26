package com.notesg3.api.controller;

import com.notesg3.api.model.Nota;
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

    @GetMapping("/{id_usuario}")
    public ResponseEntity<List<Nota>> buscarNotaPorUsuario(@PathVariable Integer id_usuario) {
        List<Nota> listaTodasNotas = notaService.buscarNotaPorUsuario(id_usuario);
        return ResponseEntity.ok(listaTodasNotas);
    }
}
