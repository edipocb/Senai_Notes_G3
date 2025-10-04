package com.notesg3.api.repository;

import com.notesg3.api.model.Nota;
import com.notesg3.api.model.NotaId;
import com.notesg3.api.model.NotaTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaTagRepository extends JpaRepository<NotaTag, Integer> {

    List<NotaTag> findByNotaIdIdNota(Integer idNota);

    List<NotaTag> findByNotaIdIdTag(Integer idTag);

    //@Query(value = "SELECT * FROM notes.nota a, notes.nota_tag b where a.descricao ILIKE %:texto% or a.titulo ILIKE %:texto%", nativeQuery = true)
    //List<NotaTag> buscaConteudoDescricao(String texto);
}
