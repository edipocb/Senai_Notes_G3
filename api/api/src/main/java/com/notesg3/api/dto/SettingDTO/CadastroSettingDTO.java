package com.notesg3.api.dto.SettingDTO;

import com.notesg3.api.model.Usuario;
import lombok.Data;

@Data
public class CadastroSettingDTO {
    private String theme;
    private String font;
    private Integer idUsuario;

}
