package com.notesg3.api.repository;

import com.notesg3.api.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByUsuarioEmail(String email);

    List<Nota> findByUsuarioEmailAndStatus(String email, boolean status);

    Nota findByIdNotaAndStatus(Integer id, boolean status);

    List<Nota> findByUsuarioEmailAndDescricaoContaining(String email, String descricao);

    @Query(value = "SELECT * FROM notes.nota a where a.descricao ILIKE %:texto% or a.titulo ILIKE %:texto%", nativeQuery = true)
    List<Nota> buscaConteudoDescricao(String texto);

    //@Query(value = "SELECT * FROM notes.nota a, notes.tag b where a.descricao ILIKE %:texto% or a.titulo ILIKE %:texto%", nativeQuery = true)
    //List<Nota> buscaConteudoDescricao(String texto);

}
