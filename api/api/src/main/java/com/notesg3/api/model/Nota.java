package com.notesg3.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Integer idNota;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "idNota", fetch =  FetchType.LAZY)
    private List<NotaTag> notaTag = new ArrayList<>();

}
