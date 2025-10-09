package com.notesg3.api.dto.usuario;

import lombok.Data;

    @Data
    public class ResetarSenhaDTO {

        // OPCIONAL - @NotBlank(message = "O campo e-mail é obrigatório.")
        // OPCIONAL - @Email(message = "Formato de e-mail inválido.")
        private String email;
    }
