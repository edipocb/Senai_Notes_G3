package com.notesg3.api.service;

import com.notesg3.api.model.NotaTag;
import com.notesg3.api.repository.NotaTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaTagService {

    private final NotaTagRepository notaTagRepository;

    public NotaTagService(NotaTagRepository notaTagRepository) {
        this.notaTagRepository = notaTagRepository;
    }

    public List<NotaTag> buscarTagPorNota(Integer idNota) {
        return notaTagRepository.findByNotaIdIdNota(idNota);
    }

    public List<NotaTag> buscarNotaPorTag(Integer idTag) {
        return notaTagRepository.findByNotaIdIdTag(idTag);
    }

}
