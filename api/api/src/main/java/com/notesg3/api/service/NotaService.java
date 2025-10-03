package com.notesg3.api.service;

import com.notesg3.api.dto.NotaDTO.NotaDTO.CadastroNotaDTO;
import com.notesg3.api.dto.NotaDTO.NotaDTO.ListaNotasPorEmailStatusDTO;
import com.notesg3.api.model.Nota;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.notesg3.api.repository.NotaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository, ConversionService conversionService) {
        this.notaRepository = notaRepository;
    }

    //Buscar todas as notas por email
    public List<Nota> buscarNotaPorEmailUsuario(String email) {
        return notaRepository.findByUsuarioEmail(email);
   }

   //Buscar Notas Arquivadas
    public List<ListaNotasPorEmailStatusDTO> buscarNotaPorEmailEStatus(String email, boolean status) {
        List<Nota> lista = notaRepository.findByUsuarioEmailAndStatus(email, status);

        return lista.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }

    public ListaNotasPorEmailStatusDTO converterParaListagemDTO(Nota nota) {
        ListaNotasPorEmailStatusDTO dto = new ListaNotasPorEmailStatusDTO();

        dto.setIdNota(nota.getIdNota());
        dto.setDescricao(nota.getDescricao());
        dto.setEmail(nota.getUsuario().getEmail());
        dto.setDataCriacao(nota.getDataCriacao());
        dto.setDataUpdate(nota.getDataUpdate());
        dto.setStatus(nota.isStatus());
        dto.setUrlImg(nota.getUrlImg());
        dto.setIdNota(nota.getIdNota());

        return dto;
    }

    //Crear Notas
    public Nota cadastroNota(CadastroNotaDTO dto) {

        Nota nota = new Nota();

        nota.setTitulo(dto.getTitulo());
        nota.setDescricao(dto.getDescricao());
        nota.setDataCriacao(dto.getDataCriacao());
        nota.setDataUpdate(dto.getDataUpdate());
        nota.setStatus(dto.isStatus());
        nota.setUrlImg(dto.getUrlImg());

        return notaRepository.save(nota);
    }

    //Buscar Nota por ID e Status
    public Nota buscarNotaIdStatus(Integer id, boolean status) {
        return notaRepository.findByIdNotaAndStatus(id, status);
    }

    //Buscar Nota por ID
    public Nota buscarNotaPorID(Integer id) {
        Optional<Nota> nota =  notaRepository.findById(id);

        if (nota.isEmpty()){
            return null;
        }
        return nota.get();
    }

    //Atualizar Nota por ID
    public Nota atualizarNota(int idNota, Nota nota) {
        Nota notaExistente = buscarNotaPorID(idNota);

        if (notaExistente == null){
            return null;
        }

        notaExistente.setStatus(nota.isStatus());
        notaExistente.setDataUpdate(nota.getDataUpdate());

        return notaRepository.save(notaExistente);
    }

    //Delete Nota por ID
    public Nota deleteNota(Integer id) {
        Nota nota = buscarNotaPorID(id);

        if (nota == null){
            return null;
        }
        notaRepository.delete(nota);
        return nota;
    }

    //Lista Nota por Email, e conteudo de Descricao
    public List<Nota> listarNotaEmailConteudoDescricao(String email, String texto){
        return notaRepository.findByUsuarioEmailAndDescricaoContaining(email, texto);
    }

    public List<Nota> buscaConteudoDescricao(String descricao){
        return notaRepository.buscaConteudoDescricao(descricao);
    }

}
