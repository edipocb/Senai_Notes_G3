package com.notesg3.api.dto.SettingDTO;

import com.notesg3.api.dto.usuario.ListarUsuarioDTO;
import lombok.Data;

@Data
public class ListaSettingDTO {
    private Integer idSetting;
    private String theme;
    private String font;
    private ListarUsuarioDTO usuario;
}
