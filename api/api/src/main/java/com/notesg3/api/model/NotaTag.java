package com.notesg3.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NotaTag")
public class NotaTag {

    @EmbeddedId
    private NotaId notaId;

    @MapsId("idNota")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_nota", nullable = false)
    private Nota idNota;

    @MapsId("idTag")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tag", nullable = false)
    private Tag idTag;
}
