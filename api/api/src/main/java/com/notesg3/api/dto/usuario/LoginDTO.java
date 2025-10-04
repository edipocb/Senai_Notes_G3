package com.notesg3.api.dto.usuario;

import com.notesg3.api.model.Usuario;
import lombok.Data;

@Data
public class LoginDTO {
    private String token;
    private ListarUsuarioDTO usuario;

}
