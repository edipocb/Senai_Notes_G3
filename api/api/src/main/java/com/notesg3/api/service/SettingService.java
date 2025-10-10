package com.notesg3.api.service;

import com.notesg3.api.dto.NotaDTO.ListaNotaDTO;
import com.notesg3.api.dto.SettingDTO.CadastroSettingDTO;
import com.notesg3.api.dto.SettingDTO.ListaSettingDTO;
import com.notesg3.api.dto.TagDTO.ListaTagDTO;
import com.notesg3.api.dto.usuario.ListarUsuarioDTO;
import com.notesg3.api.model.Nota;
import com.notesg3.api.model.Setting;
import com.notesg3.api.model.Usuario;
import com.notesg3.api.repository.SettingRepository;
import com.notesg3.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SettingService {

    private final SettingRepository settingRepository;
    private final UsuarioRepository usuarioRepository;

    public SettingService(SettingRepository repo, UsuarioRepository usuarioRepository) {
        this.settingRepository = repo;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ListaSettingDTO> listarTodos() {
        List<Setting> lista = settingRepository.findAll();

        return lista.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }

    //Metodo Comun de conversão DTO para todas as Listas de Notas
    public ListaSettingDTO converterParaListagemDTO(Setting setting) {
        ListaSettingDTO dto = new ListaSettingDTO();

        dto.setIdSetting(setting.getIdSetting());
        dto.setTheme(setting.getTheme());
        dto.setFont(setting.getFont());
        dto.setUsuario(convertUsuarioToDto(setting.getUsuario()));

        return dto;
    }

    private ListarUsuarioDTO convertUsuarioToDto(Usuario usuario) {
        ListarUsuarioDTO dto = new ListarUsuarioDTO();
        dto.setId(usuario.getIdUsuario());
        dto.setEmail(usuario.getEmail());
        return dto;
    }

    public Setting cadastroSetting(CadastroSettingDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

        Setting setting = new Setting();

        setting.setTheme(dto.getTheme());
        setting.setFont(dto.getFont());
        setting.setUsuario(usuario);

        return settingRepository.save(setting);
    }

    public ListaSettingDTO buscaPorId(Integer id) {
        Optional<Setting> setting = settingRepository.findById(id);

        if (setting.isEmpty()) {
            return null;
        }

        ListaSettingDTO dto = converterParaListagemDTO(setting.get());

        return dto;
    }

    public Setting atualizaSetting(Integer id, CadastroSettingDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

        Optional<Setting> setting = settingRepository.findById(id);

        if (setting.isEmpty()) {
            return null;
        }

        setting.get().setTheme(dto.getTheme());
        setting.get().setFont(dto.getFont());
        setting.get().setUsuario(usuario);

        return settingRepository.save(setting.get());
    }
}
