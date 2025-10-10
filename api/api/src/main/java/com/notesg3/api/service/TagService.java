package com.notesg3.api.service;

import com.notesg3.api.dto.TagDTO.CadastroTagDTO;
import com.notesg3.api.dto.TagDTO.ListaTagDTO;
import com.notesg3.api.dto.usuario.ListarUsuarioDTO;
import com.notesg3.api.model.Setting;
import com.notesg3.api.model.Tag;
import com.notesg3.api.model.Usuario;
import com.notesg3.api.repository.TagRepository;
import com.notesg3.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TagService {
    //Injecao de Dependencia
    //Falar que service depende de alguem
    //Final constante

    private final TagRepository tagRepository;
    private final UsuarioRepository usuarioRepository;

    public TagService(TagRepository tag, UsuarioRepository usuarioRepository) {
        this.tagRepository = tag;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ListaTagDTO> listarTodos(){

        List<Tag> listaTag= tagRepository.findAll();

        return listaTag.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }

    public ListaTagDTO converterParaListagemDTO(Tag tag){
        ListaTagDTO dto = new ListaTagDTO();

        dto.setIdTag(tag.getIdTag());
        dto.setNomeTag(tag.getNomeTag());
        dto.setUsuario(convertUsuarioToDto(tag.getUsuario()));

        return dto;
    }

    private ListarUsuarioDTO convertUsuarioToDto(Usuario usuario) {
        ListarUsuarioDTO dto = new ListarUsuarioDTO();
        dto.setId(usuario.getIdUsuario());
        dto.setEmail(usuario.getEmail());
        return dto;
    }


    public Tag cadastrarTag(CadastroTagDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));

        Tag tag = new Tag();
        tag.setNomeTag(dto.getNomeTag());
        tag.setUsuario(usuario);

        return tagRepository.save(tag);
    }

    public ListaTagDTO buscarPorId(Integer id){
        Optional<Tag> tag = tagRepository.findById(id);

        if (tag.isEmpty()) {
            return null;
        }

        ListaTagDTO dto = converterParaListagemDTO(tag.get());

        return dto;
    }

    public Tag deletarTagPorId(Integer id){
        Optional<Tag> tag = tagRepository.findById(id);

        if(tag.isEmpty()){
            return null;
        }

        tagRepository.delete(tag.get());
        return tag.get();
    }
    public Tag atualizarTag(Integer id, CadastroTagDTO tag){
        Usuario usuario = usuarioRepository.findById(tag.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

        Optional<Tag> tagOptional = tagRepository.findById(id);

        if(tagOptional.isEmpty()){
            return null;
        }

        tagOptional.get().setUsuario(usuario);
        tagOptional.get().setNomeTag(tag.getNomeTag());

        return tagRepository.save(tagOptional.get());
    }

    public List<ListaTagDTO> buscarTagPorEmail(String email){
        List<Tag> listaTag = tagRepository.findByUsuarioEmail(email);

        return listaTag.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }
}