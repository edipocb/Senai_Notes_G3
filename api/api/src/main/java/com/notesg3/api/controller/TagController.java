package com.notesg3.api.controller;

import com.notesg3.api.dto.TagDTO.CadastroTagDTO;
import com.notesg3.api.dto.TagDTO.ListaTagDTO;
import com.notesg3.api.model.Tag;
import com.notesg3.api.service.NotaService;
import com.notesg3.api.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
@io.swagger.v3.oas.annotations.tags.Tag(name = "TAG", description = "Operações da Tabela TAG.")
@SecurityRequirement(name = "bearerAuth")
public class TagController {
    private final TagService tagService;
    private final NotaService notaService;

    public TagController(TagService tagService, NotaService notaService) {
        this.tagService = tagService;
        this.notaService = notaService;
    }

    //Listar Todos
    @GetMapping("/listar_tags")
    @Operation(summary = "Lista todas as Tags",description = "Lista todos as Tags sem nenhuma restricao")
    public ResponseEntity<List<ListaTagDTO>> ListTodos() {
        List<ListaTagDTO> tag= tagService.listarTodos();
        return ResponseEntity.ok(tag);
    }

    @PostMapping("/cadastrar/")
    @Operation(summary = "Cadastrar Tags",description = "Cadastramos as tags de uma nota")
    public ResponseEntity<Tag> CadastrarTag(@RequestBody CadastroTagDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.cadastrarTag(dto));
    }

    @GetMapping("/buscar_tags/{id}")
    @Operation(summary = "Buscamos tag por ID",description = "Buscamos a Tag por seu ID")
    public ResponseEntity<ListaTagDTO> buscarPorId(@PathVariable Integer id){
        ListaTagDTO tagDTO= tagService.buscarPorId(id);

        return ResponseEntity.ok().body(tagDTO);
    }

    @DeleteMapping("/deletar_tags/{id}")
    @Operation(summary = "Deletamos o TAG",description = "Apagamos uma TAG por ID")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id){
        Tag tag= tagService.deletarTagPorId(id);

        if(tag==null){
            return ResponseEntity.badRequest().body("Não existe Tag com ID: " + id);
        }
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar_tags/{id}")
    @Operation(summary = "Atualizamos Tag por ID",description = "Atualizamos a Tag por seu ID")
    public ResponseEntity<?> atualizarPorId(@PathVariable Integer id, @RequestBody CadastroTagDTO dto){
        Tag tagExiste = tagService.atualizarTag(id, dto);

        if (tagExiste == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tagExiste);
    }

    @GetMapping("/listaTagEmail/{email}")
    @Operation(summary = "Buscamos tag por email",description = "Buscamos a Tag por seu email")
    public ResponseEntity<List<ListaTagDTO>> buscarPorEmail(@PathVariable String email){
        List<ListaTagDTO> lista = tagService.buscarTagPorEmail(email);
        return ResponseEntity.ok(lista);
    }
}