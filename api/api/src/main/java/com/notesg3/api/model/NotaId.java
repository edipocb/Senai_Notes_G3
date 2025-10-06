package com.notesg3.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class NotaId implements Serializable {

    @Column(name = "id_nota", nullable = false)
    private Integer idNota;

    @Column(name = "id_tag", nullable = false)
    private Integer idTag;
}
