package com.notesg3.api.service;

import com.notesg3.api.dto.usuario.CadastroUsuarioDTO;
import com.notesg3.api.dto.usuario.ListarUsuarioDTO;
import com.notesg3.api.model.Usuario;
import com.notesg3.api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {


    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository repo) {
        this.passwordEncoder = passwordEncoder;
        usuarioRepository = repo;
    }

    public Usuario cadastrarUsuario(CadastroUsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        //usuario.setSenha(dto.getSenha());
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioRepository.save(usuario);
    }

    public List<ListarUsuarioDTO> listarTodos() {
        List<Usuario> lista = usuarioRepository.findAll();

        return lista.stream().map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }

    public ListarUsuarioDTO converterParaListagemDTO(Usuario email) {
        ListarUsuarioDTO dto = new ListarUsuarioDTO();

        dto.setEmail(email.getEmail());
        return dto;
    }

    public Usuario buscarPorId(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public ListarUsuarioDTO buscarPorEmail(String email){
        Usuario u = usuarioRepository.findByEmail(email).orElse(null);
        if (u == null){
            return null;
        }

        ListarUsuarioDTO dto = new ListarUsuarioDTO();
        dto.setEmail(u.getEmail());
        dto.setId(u.getIdUsuario());
        return dto;
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
