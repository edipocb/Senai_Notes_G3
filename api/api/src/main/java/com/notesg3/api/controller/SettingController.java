package com.notesg3.api.controller;

import com.notesg3.api.model.Setting;
import com.notesg3.api.service.SettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ListarSettings")
@Tag(name = "Setting", description = "Configuracoes")
@SecurityRequirement(name = "bearerAuth")
public class SettingController {

    private SettingService settingService;
    public SettingController(SettingService service) {
        settingService = service;
    }

    @GetMapping
    @Operation(
            summary = "Listar configuracoes",
            description = "Lista todas as configs disponíveis"
    )
    public ResponseEntity<List<Setting>> listarSettings(){
        List<Setting> settings = settingService.listarTodos();
        return ResponseEntity.ok(settings);
    }



}
