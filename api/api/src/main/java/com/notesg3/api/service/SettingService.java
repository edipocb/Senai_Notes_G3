package com.notesg3.api.service;

import com.notesg3.api.model.Setting;
import com.notesg3.api.repository.SettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {

    private final SettingRepository settingRepository;

    public SettingService(SettingRepository repo) {
        settingRepository = repo;
    }

    public List<Setting> listarTodos() {
        return settingRepository.findAll();
    }
}
