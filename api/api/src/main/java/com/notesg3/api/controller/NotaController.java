package com.notesg3.api.controller;

import com.notesg3.api.dto.NotaDTO.NotaDTO.CadastroNotaDTO;
import com.notesg3.api.dto.NotaDTO.NotaDTO.ListaNotasPorEmailStatusDTO;
import com.notesg3.api.model.Nota;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.notesg3.api.service.NotaService;

import java.util.List;

@RestController
@RequestMapping("/notes/nota")
@Tag(name = "NOTA", description = "Operações da Tabela NOTA")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping("/email/{email}")
    @Operation(
            summary = "Lista de Nota por Email do Usuario",
            description = "Retorna todas as Notas, com entrada o Email do usuario."
    )
    public ResponseEntity<List<Nota>> buscarNotaPorEmailUsuario(@PathVariable String email) {
        List<Nota> listaTodasNotas = notaService.buscarNotaPorEmailUsuario(email);
        return ResponseEntity.ok(listaTodasNotas);
    }

    @GetMapping("/emailStatus/{email}/{status}")
    public ResponseEntity<List<ListaNotasPorEmailStatusDTO>>  buscarNotaPorEmailStatus(@PathVariable String email, @PathVariable boolean status) {
        List<ListaNotasPorEmailStatusDTO> listaNotaEmailStatus = notaService.buscarNotaPorEmailEStatus(email, status);
        return ResponseEntity.ok(listaNotaEmailStatus);
    }

    @PostMapping
    public ResponseEntity<Nota> cadastrarNota(@RequestBody CadastroNotaDTO dto) {
        Nota notaSalvo = notaService.cadastroNota(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(notaSalvo);
    }

    @GetMapping("/nota/{id}/{status}")
    public ResponseEntity<Nota> buscarNotaIdStatus(@PathVariable Integer id, @PathVariable boolean status) {
        Nota nota = notaService.buscarNotaIdStatus(id, status);

        if(nota == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nota);
        }

        return ResponseEntity.ok(nota);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> buscarNotaId(@PathVariable Integer id) {
        Nota nota = notaService.buscarNotaPorID(id);

        if (nota == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(nota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> atualizarNota(@PathVariable Integer id, @RequestBody Nota nota) {
        Nota notaExistente = notaService.atualizarNota(id, nota);
        if (notaExistente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notaExistente);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarNota(@PathVariable Integer id) {
        Nota nota = notaService.buscarNotaPorID(id);

        if (nota == null){
            return ResponseEntity.badRequest().body("Não existe Nota com ID: " + id);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/emailDescricao/{email}/{descricao}")
    public ResponseEntity<List<Nota>> buscarNotaEmailAndDescricao(@PathVariable String email, @PathVariable String descricao){
        List<Nota> listaNotas = notaService.listarNotaEmailConteudoDescricao(email, descricao);
        return ResponseEntity.ok(listaNotas);
    }
}
