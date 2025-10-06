package com.notesg3.api.service;

import com.notesg3.api.dto.NotaTagDTO.CadastroNotaTagDTO;
import com.notesg3.api.model.Nota;
import com.notesg3.api.model.NotaTag;
import com.notesg3.api.model.Tag;
import com.notesg3.api.repository.NotaRepository;
import com.notesg3.api.repository.NotaTagRepository;
import com.notesg3.api.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaTagService {

    private final NotaTagRepository notaTagRepository;
    private final NotaRepository notaRepository;
    private final TagRepository tagRepository;

    public NotaTagService(NotaTagRepository notaTagRepository, NotaRepository notaRepository, TagRepository tagRepository) {
        this.notaTagRepository = notaTagRepository;
        this.notaRepository = notaRepository;
        this.tagRepository = tagRepository;
    }

    public List<NotaTag> buscarTagPorNota(Integer idNota) {
        return notaTagRepository.findByNotaIdIdNota(idNota);
    }

    public List<NotaTag> buscarNotaPorTag(Integer idTag) {
        return notaTagRepository.findByNotaIdIdTag(idTag);
    }

    public NotaTag cadastrarNotaTag(CadastroNotaTagDTO dto){

        Nota nota = notaRepository.findById(dto.getIdNota())
                .orElseThrow(() -> new EntityNotFoundException("Nota não encontrado!"));

        Tag tag = tagRepository.findById(dto.getIdTag())
                .orElseThrow(() -> new EntityNotFoundException("Tag não encontrado!"));

        NotaTag notaTagSalva = new NotaTag();

        notaTagSalva.setIdNotaNotaTag(nota);
        notaTagSalva.setIdTagNotaTag(tag);

        return notaTagRepository.save(notaTagSalva);

    }
}
