package com.notesg3.api.controller;

import com.notesg3.api.dto.NotaDTO.CadastroNotaDTO;
import com.notesg3.api.dto.NotaDTO.ListaNotaDTO;
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
@Tag(name = "NOTA", description = "Operações da Tabela NOTA.")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping("/email/{email}")
    @Operation(
            summary = "Lista de Nota por Email do Usuario.",
            description = "Retorna todas as Notas por Email."
    )
    public ResponseEntity<List<ListaNotaDTO>> buscarNotaPorEmailUsuario(@PathVariable String email) {
        List<ListaNotaDTO> listaNotaPorEmail = notaService.buscarNotaPorEmailUsuario(email);
        return ResponseEntity.ok(listaNotaPorEmail);
    }

    @GetMapping("/emailStatus/{email}/{status}")
    @Operation(
            summary = "Lista de Nota por Email e Status.",
            description = "Retorna todas as Notas por Email e Status."
    )
    public ResponseEntity<List<ListaNotaDTO>>  buscarNotaPorEmailStatus(@PathVariable String email, @PathVariable boolean status) {
        List<ListaNotaDTO> listaNotaPorEmailStatus = notaService.buscarNotaPorEmailEStatus(email, status);
        return ResponseEntity.ok(listaNotaPorEmailStatus);
    }

    @GetMapping("/emailDescricao/{email}/{descricao}")
    @Operation(
            summary = "Lista de Nota por Email e conteudo na Descricao.",
            description = "Retorna todas as Notas por Email e conteudo na descricao."
    )
    public ResponseEntity<List<ListaNotaDTO>> buscarNotaEmailAndDescricao(@PathVariable String email, @PathVariable String descricao){
        List<ListaNotaDTO> listaNotas = notaService.listarNotaEmailConteudoDescricao(email, descricao);
        return ResponseEntity.ok(listaNotas);
    }

    @GetMapping("/notaDescricao/{descricao}")
    @Operation(
            summary = "Lista de Nota por conteudo na Descricao.",
            description = "Retorna todas as Notas por conteudo na Descricao."
    )
    public ResponseEntity<List<ListaNotaDTO>> buscarNotaDescricao(@PathVariable String descricao){
        List<ListaNotaDTO> listaNota = notaService.buscaConteudoDescricao(descricao);
        return ResponseEntity.ok(listaNota);
    }

    @PostMapping
    @Operation(
            summary = "Cadastro de Nota.",
            description = "Cadastro de Nota."
    )
    public ResponseEntity<Nota> cadastrarNota(@RequestBody CadastroNotaDTO dto) {
        Nota notaSalvo = notaService.cadastroNota(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(notaSalvo);
    }

    @GetMapping("/nota/{id}/{status}")
    @Operation(
            summary = "Buscar Nota por Id e Status.",
            description = "Buscar Nota por Id e Status."
    )
    public ResponseEntity<Nota> buscarNotaIdStatus(@PathVariable Integer id, @PathVariable boolean status) {
        Nota nota = notaService.buscarNotaIdStatus(id, status);

        if(nota == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nota);
        }

        return ResponseEntity.ok(nota);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar Nota por Id.",
            description = "Buscar Nota por Id."
    )
    public ResponseEntity<Nota> buscarNotaId(@PathVariable Integer id) {
        Nota nota = notaService.buscarNotaPorID(id);

        if (nota == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(nota);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar Nota por Id.",
            description = "Atualizar Nota por Id."
    )
    public ResponseEntity<Nota> atualizarNota(@PathVariable Integer id, @RequestBody Nota nota) {
        Nota notaExistente = notaService.atualizarNota(id, nota);
        if (notaExistente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notaExistente);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Nota por Id.",
            description = "Delete Nota por Id."
    )
    public ResponseEntity<?> deletarNota(@PathVariable Integer id) {
        Nota nota = notaService.deleteNota(id);

        if (nota == null){
            return ResponseEntity.badRequest().body("Não existe Nota com ID: " + id);
        }

        return ResponseEntity.noContent().build();
    }

}
