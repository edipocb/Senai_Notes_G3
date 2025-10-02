package com.notesg3.api.repository;


import com.notesg3.api.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {

    List<Tag> findByUsuarioEmail(String email);

    Tag findByUsuarioEmailAndNomeTag(String email, String nome);

}
