package com.notesg3.api.repository;

import com.notesg3.api.model.NotaId;
import com.notesg3.api.model.NotaTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaTagRepository extends JpaRepository<NotaTag, Integer> {

    List<NotaTag> findByNotaIdIdNota(Integer idNota);
}
