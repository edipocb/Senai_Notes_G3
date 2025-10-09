package com.notesg3.api.repository;

import com.notesg3.api.model.NotaTag;
import com.notesg3.api.model.NotaTagId;
import com.notesg3.api.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaTagRepository extends JpaRepository<NotaTag, Integer> {
      NotaTag findByIdTag(Tag idTag);

//    List<NotaTag> findByNotaTagIdIdNota(Integer idNota);
}
