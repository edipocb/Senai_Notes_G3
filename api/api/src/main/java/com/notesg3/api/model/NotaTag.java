package com.notesg3.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "nota_tag")
public class NotaTag {

    @EmbeddedId
    private NotaTagId id;

    @MapsId("idAnotacao")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_anotacao", nullable = false)
    private Nota idAnotacao;

    @MapsId("idTag")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tag", nullable = false)
    private Tag idTag;
}
