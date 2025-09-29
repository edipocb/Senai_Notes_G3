package com.notesg3.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Nota")
@IdClass(NotaId.class)

public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota", nullable = false)
    private Integer idNota;

    @Id
    @Column(name = "id_tag", nullable = false)
    private Integer idTag;

    @Column(name = "titulo", nullable = false, columnDefinition = "TEXT",unique = true)
    private String titulo;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_criacao", nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(name = "data_update", nullable = false)
    private OffsetDateTime dataUpdate;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "url_img", nullable = false, columnDefinition = "TEXT")
    private String urlImg;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tag", insertable = false, updatable = false)
    private Tag tag;
}
