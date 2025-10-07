package com.notesg3.api.controller;

import com.notesg3.api.dto.TagDTO.CadastroTagDTO;
import com.notesg3.api.model.Tag;
import com.notesg3.api.service.NotaService;
import com.notesg3.api.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@SecurityRequirement(name = "bearerAuth")
@io.swagger.v3.oas.annotations.tags.Tag(name = "TAG", description = "Operações da Tabela TAG.")
public class TagController {
    private final TagService tagService;
    private final NotaService notaService;

    public TagController(TagService tagService, NotaService notaService) {
        this.tagService = tagService;
        this.notaService = notaService;
    }

    //Listar Todos
    @GetMapping("/LISTAR_TAGS")

    @Operation(
            summary = "Lista todas as Tags",
            description = "Lista todos as Tags sem nenhuma restricao"
    )
    public ResponseEntity<List<Tag>> ListTodos() {
        //1.Pegar a lista de todas as tags

        List<Tag> tag= tagService.listarTodos();
        return ResponseEntity.ok(tag);
    }
    @PostMapping("/CADASTRAR/")
    public ResponseEntity<Tag> CadastrarTag(@RequestBody CadastroTagDTO dto){
        //1.TENTAR CADASTRAR A TAG
        //CODIGO 200-04
        //RETURN RESPONSEeNTITY.OK(CLIENTE);
        //CODIGO 201-CREATE
        return ResponseEntity.status(
                HttpStatus.CREATED).body(
                        tagService.cadastrarTag(dto));
    }
    //BUSCAR TAG POR ID
    //GET, POST, PUT, DELETE
    @GetMapping("/{id}/BUSCAR_TAGS")
    //path variable --> recebe um valor no link
    //requeste body-->
    public ResponseEntity<Tag> buscarPorId(
            @PathVariable Integer id){
        //1.Procurar a tag
        Tag tag= tagService.buscarPorId(id);

        //2. se nao encontrar retorna um erro

        if(tag==null){
            //MAIS SIMPLE:
            //RETURN RESPONSE ENTITY.NOTFOUND().BUILD();
            //MAIS DETALHE:
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(null);
        }
        //3. se encontrat, retorna a tag
        return ResponseEntity.ok(tag);
    }

    @DeleteMapping("/{id}/DELETAR_TAGS")
    public ResponseEntity<?> deletarPorId(
            @PathVariable Integer id){
        //1. verifica se a tag existe
        Tag tag= tagService.buscarPorId(id);

        //2. se nao existir retorno erro
        if(tag==null){
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND).body(null);
        }

        //3. se existir , retorna ok
        return ResponseEntity.ok(tag);
    }
    @PutMapping("/{id}/ATUALIZAR_TAGS")
    public ResponseEntity<?> atualizarPorId(
            @PathVariable Integer id, @RequestBody Tag tag){
        //1. tento atualizar a tag
        Tag tag1= tagService.buscarPorId(id);

        //2.se nao achar a TAG, mostra o erro
        if(tag1==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        //3. se achar retorna ok
        return ResponseEntity.ok(tag);
    }

    @GetMapping("/listaTagEmail/{email}")
    public ResponseEntity<List<Tag>> buscarPorEmail(@PathVariable String email){
        List<Tag> lista = tagService.buscarTagPorEmail(email);
        return ResponseEntity.ok(lista);
    }

}
