package com.notesg3.api.controller;

import com.notesg3.api.model.Tag;
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
public class TagController {
    private final TagService tagService;
    public TagController(TagService tagService) {
        this.tagService = tagService;
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
    public ResponseEntity<Tag> CadastrarTag(
            @RequestBody Tag tag){
        //1.TENTAR CADASTRAR A TAG
        //CODIGO 200-04
        //RETURN RESPONSEeNTITY.OK(CLIENTE);
        //CODIGO 201-CREATE
        return ResponseEntity.status(
                HttpStatus.CREATED).body(
                        tagService.CadastrarTag(tag));
    }
    //BUSCAR TAG POR ID
    //GET, POST, PUT, DELETE
    @GetMapping("/BUSCAR_TAGS/{id}")
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

    @DeleteMapping("/DELETAR_TAGS/{id}")
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
    @PutMapping("/ATUALIZAR_TAGS/{id}")
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

}
