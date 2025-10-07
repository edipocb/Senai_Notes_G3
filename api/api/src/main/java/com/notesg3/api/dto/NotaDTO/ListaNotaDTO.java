package com.notesg3.api.dto.NotaDTO;

import com.notesg3.api.dto.TagDTO.ListaTagDTO;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

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
    private List<ListaTagDTO> tags;

}
