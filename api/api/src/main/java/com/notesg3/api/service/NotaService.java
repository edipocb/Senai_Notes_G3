package com.notesg3.api.service;

import com.notesg3.api.model.Nota;
import org.springframework.stereotype.Service;
import com.notesg3.api.repository.NotaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    //Buscar todas as notas por email
    public List<Nota> buscarNotaPorEmailUsuario(String email) {
        return notaRepository.findByUsuarioEmail(email);
   }

    //Crear Notas
    public Nota cadastroNota(Nota nota) {
        return notaRepository.save(nota);
    }

    //Buscar Nota por ID e Status
    public Nota buscarNotaIdStatus(Integer id, boolean status) {
        return notaRepository.findByIdNotaAndStatus(id, status);
    }

}
