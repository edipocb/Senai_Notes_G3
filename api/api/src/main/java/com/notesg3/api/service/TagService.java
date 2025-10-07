package com.notesg3.api.service;


import com.notesg3.api.model.Tag;
import com.notesg3.api.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagService {
    //Injecao de Dependencia
    //Falar que service depende de alguem
    //Final constante

    private final TagRepository tagRepository;

    public TagService(TagRepository tag) {
        tagRepository = tag;
    }
    //Listar todas as Tags

    public List<Tag> listarTodos(){

        return tagRepository.findAll();
    }

    //Insert Into
    public Tag CadastrarTag(Tag tag){
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






}
