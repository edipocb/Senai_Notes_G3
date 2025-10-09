package com.notesg3.api.controller;

import com.notesg3.api.dto.usuario.CadastroUsuarioDTO;
import com.notesg3.api.dto.usuario.ListarUsuarioDTO;
import com.notesg3.api.dto.usuario.ResetarSenhaDTO;
import com.notesg3.api.model.Usuario;
import com.notesg3.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Usuarios")
@Tag(name = "Usuarios", description = "Operacoes relacionadas ao usuario")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

        private UsuarioService usuarioService;

        public UsuarioController(UsuarioService service) {
            usuarioService = service;
        }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ResetarSenhaDTO resetarSenhaDTO) {
        usuarioService.recuperarSenha(resetarSenhaDTO.getEmail());
        return ResponseEntity.ok("Se um usuário com este e-mail existir, uma nova senha será enviada.");
    }

        @PostMapping("/cadastrarUser")
        @Operation(
                summary = "Cadastrar usuario",
                description = "Realizar o cadastro de usuario"
        )
        public ResponseEntity<CadastroUsuarioDTO> cadastrarUsuario (
                @RequestBody CadastroUsuarioDTO usuario
        ) {
            usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }

        @GetMapping("/listar")
        @Operation(
                summary = "Lista todos os usuarios",
                description = "Lista todos os clientes sem nenhuma restricao"
        )
    public ResponseEntity<List<ListarUsuarioDTO>> listarUsuarios(){
            List<ListarUsuarioDTO> usuario = usuarioService.listarTodos();
            return ResponseEntity.ok(usuario);
        }

    @GetMapping("/buscar/{id}")
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
            }
            return ResponseEntity.ok(usuario);
    }

}

