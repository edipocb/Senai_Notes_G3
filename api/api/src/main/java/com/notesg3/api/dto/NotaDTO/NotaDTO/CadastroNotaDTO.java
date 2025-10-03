package com.notesg3.api.dto.NotaDTO.NotaDTO;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CadastroNotaDTO {

    private String titulo;
    private String descricao;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataUpdate;
    private boolean status;
    private String urlImg;
    private Integer idUsuario;
    List<String> tags;
}
