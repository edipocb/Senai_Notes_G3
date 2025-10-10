package com.notesg3.api.dto.TagDTO;

import com.notesg3.api.dto.usuario.ListarUsuarioDTO;
import lombok.Data;

@Data
public class ListaTagDTO {
    private Integer idTag;
    private String nomeTag;
    private ListarUsuarioDTO usuario;
}
