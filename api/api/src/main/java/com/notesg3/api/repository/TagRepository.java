package com.notesg3.api.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Optional<Tag> findByNomeTagAndIdUsuario(String nomeTag, Integer idUsuario);

}
