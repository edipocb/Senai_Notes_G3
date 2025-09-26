package com.notesg3.api.service;

import com.notesg3.api.model.Nota;
import org.springframework.stereotype.Service;
import com.notesg3.api.repository.NotaRepository;

import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    //Buscar todas as notas
    public List<Nota> buscarNotaPorUsuario(Integer idUsuario) {
        return notaRepository.findById_usuarioEquals();
    }



}
