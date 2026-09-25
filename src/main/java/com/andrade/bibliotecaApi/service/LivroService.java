package com.andrade.bibliotecaApi.service;

import com.andrade.bibliotecaApi.dto.ApiResponse;
import com.andrade.bibliotecaApi.dto.AutorDTO;
import com.andrade.bibliotecaApi.dto.ExemplarDTO;
import com.andrade.bibliotecaApi.dto.LivroDTO;
import com.andrade.bibliotecaApi.entity.Autor;
import com.andrade.bibliotecaApi.entity.Livro;
import com.andrade.bibliotecaApi.exceptions.BadRequestException;
import com.andrade.bibliotecaApi.repository.AutorRepository;
import com.andrade.bibliotecaApi.repository.LivroRepository;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Nodes.collect;

@Service
public class LivroService {
    private static final Logger log = LoggerFactory.getLogger(LivroService.class);
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    AutorRepository autorRepository;

    public ResponseEntity<ApiResponse<LivroDTO>> cadastrarLivro(String titulo, int ano, String genero, String nomeAutor) {
        Autor autor = autorRepository.findByNome(nomeAutor)
                .orElseThrow(() -> new NoResultException("Autor informado não localizado para cadastro do livro"));

        livroRepository.save(new Livro(titulo, ano, genero, autor));
        LivroDTO dto = new LivroDTO(titulo, ano, genero, new AutorDTO(autor.getNome(), autor.getNacionalidade()));

        return ResponseEntity.ok(new ApiResponse<>(true,  "Livro cadastrado com sucesso! ", dto));
    }

    public LivroDTO consultaLivro(String id, String titulo) {
        if ((id != null && !id.isBlank()) || (titulo != null && !titulo.isBlank())) {
            Livro livro = livroRepository.findById(id != null ? id : titulo).orElseThrow(() -> new NoResultException("Autor não encontrado"));

            AutorDTO autorDto = new AutorDTO(livro.getAutor().getNome(), livro.getAutor().getNacionalidade());
            return new LivroDTO(livro.getTitulo(), livro.getAno(), livro.getGenero(), autorDto);
        }

        throw new BadRequestException("Necessário informar ao menos um parâmetro para consulta");
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
            livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        } else if (titulo != null && !titulo.isBlank()) {
            livro = livroRepository.findByTitulo(titulo).orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        } else {
            throw new RuntimeException("Necessário informar ao menos um parâmetro para consulta");
        }
        livroRepository.delete(livro);
        return new LivroDTO(livro.getTitulo(), livro.getAno(), livro.getGenero(),
               new AutorDTO(livro.getAutor().getNome(), livro.getAutor().getNacionalidade()));
    }

    public List<ExemplarDTO> cadastroExemplar(ExemplarDTO exemplarDTO, Integer quantidade) {
        List<ExemplarDTO> retorno = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {

        }
    }
}
