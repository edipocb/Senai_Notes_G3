package com.notesg3.api.repository;


import com.notesg3.api.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Optional<Tag> findByNomeTagAndUsuarioIdUsuario(String nomeTag, Integer idUsuario);

    List<Tag> findByUsuarioEmail(String email);

}
