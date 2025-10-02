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

   public List<Nota> buscarNotaPorTagName(String tagName) {
        return notaRepository.findByTagNomeTagContainingIgnoreCase(
                tagName);
   }

    //Crear Notas
    public Nota cadastroNota(Nota nota) {
        return notaRepository.save(nota);
    }

    public Nota buscarNotaPorIdeEmail(Integer idNota, String email) {
        Optional<Nota> notaExiste = notaRepository.findByIdNotaAndUsuarioEmail(idNota, email);

        if (notaExiste.isEmpty()) {
            return null;
        }
        return notaExiste.get();
    }
}
