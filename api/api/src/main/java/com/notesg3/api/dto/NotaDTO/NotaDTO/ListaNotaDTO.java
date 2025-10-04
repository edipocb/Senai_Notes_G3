package com.notesg3.api.dto.NotaDTO.NotaDTO;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ListaNotaDTO {

    private Integer idNota;
    private String email;
    private String descricao;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataUpdate;
    private boolean status;
    private String urlImg;
    private Integer idUsuario;

}
