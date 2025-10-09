package com.notesg3.api.service;

import com.notesg3.api.dto.NotaDTO.CadastroNotaDTO;
import com.notesg3.api.dto.NotaDTO.ListaNotaDTO;
import com.notesg3.api.dto.NotaTagDTO.CadastroNotaTagDTO;
import com.notesg3.api.dto.TagDTO.ListaTagDTO;
import com.notesg3.api.dto.usuario.ListarUsuarioDTO;
import com.notesg3.api.model.*;
import com.notesg3.api.repository.NotaTagRepository;
import com.notesg3.api.repository.TagRepository;
import com.notesg3.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.notesg3.api.repository.NotaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final TagRepository tagRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotaTagRepository notaTagRepository;
    private final NotaTagService notaTagService;

    public NotaService(NotaRepository notaRepository, ConversionService conversionService, TagRepository tagRepository, UsuarioRepository usuarioRepository, NotaTagRepository notaTagRepository, NotaTagService notaTagService) {
        this.notaRepository = notaRepository;
        this.tagRepository = tagRepository;
        this.usuarioRepository = usuarioRepository;
        this.notaTagRepository = notaTagRepository;
        this.notaTagService = notaTagService;
    }

    //Listar notas por email
    public List<ListaNotaDTO> buscarNotaPorEmailUsuario(String email) {

        List<Nota> lista = notaRepository.findByUsuarioEmail(email);

        return lista.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
   }

   //Listar Notas por email e status
    public List<ListaNotaDTO> buscarNotaPorEmailEStatus(String email, boolean status) {
        List<Nota> lista = notaRepository.findByUsuarioEmailAndStatus(email, status);

        return lista.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }

    //Listar Notas por Email, e conteudo de Descricao
    public List<ListaNotaDTO> listarNotaEmailConteudoDescricao(String email, String texto){
        List<Nota> lista = notaRepository.findByUsuarioEmailAndDescricaoContaining(email, texto);

        return lista.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }

    //Listar Notas por conteudo de Descricao
    public List<ListaNotaDTO> buscaConteudoDescricao(String descricao){
        List<Nota> lista = notaRepository.buscaConteudoDescricao(descricao);

        return lista.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }

    //Metodo Comun de conversão DTO para todas as Listas de Notas
    public ListaNotaDTO converterParaListagemDTO(Nota nota) {
        ListaNotaDTO dto = new ListaNotaDTO();

        dto.setIdNota(nota.getIdNota());
        dto.setDescricao(nota.getDescricao());
        dto.setEmail(nota.getUsuario().getEmail());
        dto.setDataCriacao(nota.getDataCriacao());
        dto.setDataUpdate(nota.getDataUpdate());
        dto.setStatus(nota.isStatus());
        dto.setUrlImg(nota.getUrlImg());
        dto.setIdNota(nota.getIdNota());

        dto.setUsuario(convertUsuarioToDto(nota.getUsuario()));

        List<ListaTagDTO> tagsDto = nota.getNotaTag().stream()
                .map(tagAnotacao -> convertTagToDto(tagAnotacao.getIdTag()))
                .collect(Collectors.toList());
        dto.setTag(tagsDto);

        return dto;
    }

    private ListarUsuarioDTO convertUsuarioToDto(Usuario usuario) {
        ListarUsuarioDTO dto = new ListarUsuarioDTO();
        dto.setId(usuario.getIdUsuario());
        dto.setEmail(usuario.getEmail());
        return dto;
    }

    private ListaTagDTO convertTagToDto(Tag tag) {
        ListaTagDTO dto = new ListaTagDTO();
        dto.setIdTag(tag.getIdTag());
        dto.setNomeTag(tag.getNomeTag());
        return dto;
    }

    //Crear Notas
    public Nota cadastroNota(CadastroNotaDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

        Nota nota = new Nota();
        nota.setTitulo(dto.getTitulo());
        nota.setDescricao(dto.getDescricao());
        nota.setStatus(dto.isStatus());
        nota.setUrlImg(dto.getUrlImg());
        nota.setUsuario(usuario);
        nota.setDataCriacao(OffsetDateTime.now());
        nota.setDataUpdate(OffsetDateTime.now());

        Nota notaSalva = notaRepository.save(nota);
        //Tag novaTag = new Tag();
        for(String nomeTag : dto.getTags()){
            //Cadastramos na tabela Tag
            Tag tag = tagRepository.findByNomeTagAndUsuarioIdUsuario(nomeTag, usuario.getIdUsuario())
                    .orElseGet(() -> {
                        Tag novaTag = new Tag();
                        novaTag.setNomeTag(nomeTag);
                        novaTag.setUsuario(usuario);

                        return tagRepository.save(novaTag);
                    });

            //Cadastro na tabela NotaTag - Intermediaria
            NotaTagId notaTagId = new NotaTagId();
            notaTagId.setIdAnotacao(notaSalva.getIdNota());
            notaTagId.setIdTag(notaTagId.getIdTag());

            NotaTag notaTagSalva = new NotaTag();
            notaTagSalva.setId(notaTagId);
            notaTagSalva.setIdAnotacao(notaSalva);
            notaTagSalva.setIdTag(tag);

            notaTagRepository.save(notaTagSalva);
        }

        return notaSalva;
    }

    //Buscar Nota por ID e Status
    public Nota buscarNotaIdStatus(Integer id, boolean status) {
        return notaRepository.findByIdNotaAndStatus(id, status);
    }

    //Buscar Nota por ID //aqui
    public ListaNotaDTO buscarNotaPorID(Integer id) {
        Optional<Nota> nota =  notaRepository.findById(id);

        if (nota.isEmpty()){
            return null;
        }

        ListaNotaDTO dto = converterParaListagemDTO(nota.get());
        return dto;
    }

    //Atualizar Nota por ID
    public Nota atualizarNota(Integer idNota, Nota nota) {
        Optional<Nota> notaExistente = notaRepository.findById(idNota);

        if (notaExistente.isEmpty()){
            return null;
        }

        notaExistente.get().setStatus(nota.isStatus());
        notaExistente.get().setDataUpdate(nota.getDataUpdate());

        return notaRepository.save(notaExistente.get());
    }

    //Delete Nota por ID
    public Nota deleteNota(Integer id) {
        Optional<Nota> notaExistente = notaRepository.findById(id);

        if (notaExistente.isEmpty()){
            return null;
        }
        notaRepository.delete(notaExistente.get());
        return notaExistente.get();
    }

    public List<ListaNotaDTO> listarAnotacoesPorEmail (String email) {
        List<Nota> anotacoes = notaRepository.findByUsuarioEmailCompleto(email);

        return anotacoes.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }
}
