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

public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota", nullable = false)
    private Integer id_nota;

    @Column(name = "titulo", nullable = false, columnDefinition = "TEXT",unique = true)
    private String titulo;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_criacao", nullable = false)
    private OffsetDateTime data_criacao;

    @Column(name = "data_update", nullable = false)
    private OffsetDateTime data_update;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "url_img", nullable = false, columnDefinition = "TEXT")
    private String url_img;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
