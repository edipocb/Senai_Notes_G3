package com.notesg3.api.service;

import com.notesg3.api.dto.TagDTO.CadastroTagDTO;
import com.notesg3.api.model.Tag;
import com.notesg3.api.model.Usuario;
import com.notesg3.api.repository.TagRepository;
import com.notesg3.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    //Listar todas as Tags

    public List<Tag> listarTodos(){

        return tagRepository.findAll();
    }

    //Insert Into
    public Tag cadastrarTag(CadastroTagDTO dto){

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));

        Tag tag = new Tag();

        tag.setNomeTag(dto.getNomeTag());
        tag.setUsuario(usuario);

        return tagRepository.save(tag);


    }

    public Tag buscarPorId(Integer id){
        return tagRepository.findById(id).orElse(null);

    }

    public Tag deletarTagPorId(Integer id){
        //1.verificar se a tag ja existe
        Tag tag = buscarPorId(id);

        //2. se nao existir, retorno nulo
        if(tag == null){
            return null;
        }
        //3.se existir, excluo
        tagRepository.delete(tag);
        return tag;
    }
    public Tag atualizarTag(Integer id, Tag tag){
        //1.Procurar quem eu quero atualizar
        Tag tagAnterior = buscarPorId(id);

        //2.Se eu nao achar retorno null
        if(tagAnterior == null){
            return null;
        }
        //3. se eu achar a tag eu atualizo
        tagAnterior.setNomeTag(tag.getNomeTag());
        return tagRepository.save(tagAnterior);
    }


    public List<Tag> buscarTagPorEmail(String email){
        return tagRepository.findByUsuarioEmail(email);
    }





}
