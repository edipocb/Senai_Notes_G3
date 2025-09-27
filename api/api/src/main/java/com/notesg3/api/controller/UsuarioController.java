package com.notesg3.api.controller;

import com.notesg3.api.model.Usuario;
import com.notesg3.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ListarClientes")
@Tag(name = "Usuarios", description = "Operacoes relacionadas ao usuario")
public class UsuarioController {

        private UsuarioService usuarioService;

        public UsuarioController(UsuarioService service) {
            usuarioService = service;
        }

        @PostMapping("/cadastrarUser")
        @Operation(
                summary = "Cadastrar usuario",
                description = "Realizar o cadastro de usuario"
        )
        public ResponseEntity<Usuario> cadastrarUsuario (
                @RequestBody Usuario usuario
        ) {
            usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }

        @GetMapping
        @Operation(
                summary = "Lista todos os clientes",
                description = "Lista todos os clientes sem nenhuma restricao"
        )
    public ResponseEntity<List<Usuario>> listarUsuarios(){
            List<Usuario> usuarios = usuarioService.listarTodos();
            return ResponseEntity.ok(usuarios);
        }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(
    @PathVariable Integer id) {
            Usuario usuario = usuarioService.buscarPorId(id);

            if (usuario == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
                 }
            return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar usuario",
            description = "Deletar usuario atraves do ID"
    )
    public ResponseEntity<?> deletarUsuarioPorId(
            @PathVariable Integer id) {
            Usuario usuario = usuarioService.deletarUser(id);
            if (usuario == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuerio nao encontrado");
            }
            return ResponseEntity.ok(usuario);
    }

}

