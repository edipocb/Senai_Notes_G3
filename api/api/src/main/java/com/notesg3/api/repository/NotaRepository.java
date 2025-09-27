package com.notesg3.api.repository;

import com.notesg3.api.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByUsuarioEmail(String email);

    List<Nota> findByTagNomeTagContainingIgnoreCase(String nome);

    Optional<Nota> findByIdNotaAndUsuarioEmail(Integer idNota, String email);


}
