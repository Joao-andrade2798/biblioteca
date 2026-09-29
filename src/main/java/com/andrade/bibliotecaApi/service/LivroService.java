package com.andrade.bibliotecaApi.service;

import com.andrade.bibliotecaApi.dto.request.ExemplarRequest;
import com.andrade.bibliotecaApi.dto.response.ApiResponse;
import com.andrade.bibliotecaApi.dto.response.AutorDTO;
import com.andrade.bibliotecaApi.dto.response.ExemplarDTO;
import com.andrade.bibliotecaApi.dto.response.LivroDTO;
import com.andrade.bibliotecaApi.entity.Autor;
import com.andrade.bibliotecaApi.entity.Exemplar;
import com.andrade.bibliotecaApi.entity.Livro;
import com.andrade.bibliotecaApi.enums.StatusExemplarEnum;
import com.andrade.bibliotecaApi.exceptions.BadRequestException;
import com.andrade.bibliotecaApi.exceptions.UnprocessableException;
import com.andrade.bibliotecaApi.repository.AutorRepository;
import com.andrade.bibliotecaApi.repository.ExemplarRepository;
import com.andrade.bibliotecaApi.repository.LivroRepository;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {
    private static final Logger log = LoggerFactory.getLogger(LivroService.class);
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    AutorRepository autorRepository;
    @Autowired
    ExemplarRepository exemplarRepository;

    public ResponseEntity<ApiResponse<LivroDTO>> cadastrarLivro(String titulo, int ano, String genero, String nomeAutor) {
        Autor autor = autorRepository.findByNome(nomeAutor)
                .orElseThrow(() -> new NoResultException("Autor informado não localizado para cadastro do livro"));

        livroRepository.save(new Livro(titulo, ano, genero, autor));
        LivroDTO dto = new LivroDTO(titulo, ano, genero, new AutorDTO(autor.getNome(), autor.getNacionalidade()));

        return ResponseEntity.ok(new ApiResponse<>(true,  "Livro cadastrado com sucesso! ", dto));
    }

    public LivroDTO consultaLivro(String id, String titulo) {
        Livro livro = null;
        if ((id != null && !id.isBlank())) {
            livro = livroRepository.findById(id).orElseThrow(() -> new NoResultException("Livro não encontrado"));
        } else if (titulo != null && !titulo.isBlank()) {
            livro = livroRepository.findByTitulo(titulo).orElseThrow(() -> new NoResultException("Livro não encontrado"));
        } else {
            throw new BadRequestException("Necessário informar ao menos um parâmetro para consulta");
        }

        AutorDTO autorDto = new AutorDTO(livro.getAutor().getNome(), livro.getAutor().getNacionalidade());
        return new LivroDTO(livro.getTitulo(), livro.getAno(), livro.getGenero(), autorDto);
    }

    public ResponseEntity<ApiResponse<List<LivroDTO>>> listaLivros() {
        List<LivroDTO> livros = livroRepository.findAll().stream()
                .map(livro -> new LivroDTO(
                        livro.getTitulo(),
                        livro.getAno(),
                        livro.getGenero(),
                        new AutorDTO(livro.getAutor().getNome(), livro.getGenero())
                ))
                .toList();

        return ResponseEntity.ok(new ApiResponse<>(true, "Lista de Livros:", livros));
    }

    public LivroDTO excluirLivros(String id, String titulo){
        Livro livro = null;

        if (id != null && !id.isBlank()) {
            livro = livroRepository.findById(id).orElseThrow(() -> new NoResultException("Livro não encontrado"));
        } else if (titulo != null && !titulo.isBlank()) {
            livro = livroRepository.findByTitulo(titulo).orElseThrow(() -> new NoResultException("Livro não encontrado"));
        } else {
            throw new BadRequestException("Necessário informar ao menos um parâmetro para consulta");
        }

        livroRepository.delete(livro);
        return new LivroDTO(livro.getTitulo(), livro.getAno(), livro.getGenero(),
               new AutorDTO(livro.getAutor().getNome(), livro.getAutor().getNacionalidade()));
    }

    public List<ExemplarDTO> cadastroExemplar(ExemplarRequest request) {
        Livro livro = livroRepository.findById(request.idLivro()).orElseThrow(() -> new UnprocessableException("Livro não encontrado"));

        LivroDTO livroDTO = new LivroDTO(livro.getTitulo(), livro.getAno(), livro.getGenero(), new AutorDTO(livro.getAutor().getNome(), livro.getAutor().getNacionalidade()));

        List<ExemplarDTO> retorno = new ArrayList<>();

        for (int i = 0; i < request.quantidade(); i++) {
            Exemplar exemplar = exemplarRepository.save(new Exemplar(livro, StatusExemplarEnum.DISPONIVEL));
            retorno.add(new ExemplarDTO(exemplar.getCodigo(), livroDTO, exemplar.getStatus()));
        }

        return retorno;
    }
}
