package com.notesg3.api.service;

import com.notesg3.api.dto.usuario.CadastroUsuarioDTO;
import com.notesg3.api.model.Usuario;
import com.notesg3.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository repo) {
        usuarioRepository = repo;
    }

    public Usuario cadastrarUsuario(CadastroUsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario deletarUser(Integer id){
        Usuario usuario = buscarPorId(id);
        if (usuario == null){
            return null;
        }
        usuarioRepository.delete(usuario);
        return usuario;
    }

}
