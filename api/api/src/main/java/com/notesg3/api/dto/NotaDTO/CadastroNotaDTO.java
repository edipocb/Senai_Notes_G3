package com.notesg3.api.dto.NotaDTO;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CadastroNotaDTO {

    private String titulo;
    private String descricao;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataUpdate;
    private boolean status;
    private String urlImg;
    private Integer idUsuario;
}
