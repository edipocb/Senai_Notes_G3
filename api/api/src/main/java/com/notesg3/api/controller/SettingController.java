package com.notesg3.api.controller;

import com.notesg3.api.dto.NotaDTO.CadastroNotaDTO;
import com.notesg3.api.dto.SettingDTO.CadastroSettingDTO;
import com.notesg3.api.dto.SettingDTO.ListaSettingDTO;
import com.notesg3.api.model.Nota;
import com.notesg3.api.model.Setting;
import com.notesg3.api.service.SettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
@Tag(name = "Setting", description = "Configuracoes")
@SecurityRequirement(name = "bearerAuth")
public class SettingController {

    private SettingService settingService;

    public SettingController(SettingService service) {

        this.settingService = service;
    }

    @GetMapping
    @Operation(summary = "Listar configuracoes", description = "Lista todas as configs disponíveis")
    public ResponseEntity<List<ListaSettingDTO>> listarSettings(){
        List<ListaSettingDTO> settings = settingService.listarTodos();
        return ResponseEntity.ok(settings);
    }

    @PostMapping
    @Operation(summary = "Cadastro de configuracoes", description = "Cadastro inicial das configurações")
    public ResponseEntity<Setting> cadastroSettings(@RequestBody CadastroSettingDTO dto) {
        Setting setting = settingService.cadastroSetting(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(setting);

    }

    @GetMapping("/idSetting/{idSetting}")
    @Operation(summary = "Busca Setting por ID", description = "Buscamos as configurações por ID")
    public ResponseEntity<ListaSettingDTO> buscarSettingPorId(@PathVariable Integer idSetting) {
        ListaSettingDTO settingDTO = settingService.buscaPorId(idSetting);

        return ResponseEntity.ok().body(settingDTO);
    }

    @PutMapping("{idSetting}")
    @Operation(summary = "Atualizar Setting por Id.", description = "Atualizar configuraçóes por Id.")
    public ResponseEntity<Setting> atualizarSetting(@PathVariable Integer idSetting, @RequestBody CadastroSettingDTO dto) {
        Setting setting = settingService.atualizaSetting(idSetting, dto);

        if (setting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(setting);
    }
}
