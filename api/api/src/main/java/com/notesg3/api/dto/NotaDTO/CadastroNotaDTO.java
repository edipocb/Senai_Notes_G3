package com.notesg3.api.dto.NotaDTO;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CadastroNotaDTO {

    private String titulo;
    private String descricao;
    private boolean status;
    private String urlImg;
    private Integer idUsuario;
    List<String> tags;
}
