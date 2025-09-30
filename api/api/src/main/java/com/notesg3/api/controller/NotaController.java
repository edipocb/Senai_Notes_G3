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

    @PostMapping
    public ResponseEntity<Nota> cadastrarNota(@RequestBody Nota nota) {
        notaService.cadastroNota(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nota);
    }

    @GetMapping("/nota/{id}/{status}")
    public ResponseEntity<Nota> buscarNotaIdStatus(@PathVariable Integer id, @PathVariable boolean status) {
        Nota nota = notaService.buscarNotaIdStatus(id, status);

        if(nota == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nota);
        }

        return ResponseEntity.ok(nota);
    }
}
