package com.andrade.bibliotecaApi.service;

import com.andrade.bibliotecaApi.dto.ApiResponse;
import com.andrade.bibliotecaApi.dto.AutorDTO;
import com.andrade.bibliotecaApi.dto.LivroDTO;
import com.andrade.bibliotecaApi.entity.Autor;
import com.andrade.bibliotecaApi.entity.Livro;
import com.andrade.bibliotecaApi.repository.AutorRepository;
import com.andrade.bibliotecaApi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    AutorRepository autorRepository;

    public ResponseEntity<ApiResponse<Livro>> cadastrarLivro(String titulo, int ano, String genero, String nomeAutor) {
        Autor autor = autorRepository.findByNome(nomeAutor)
                .orElse(null);

        if (autor == null){
            return ResponseEntity.ok(new ApiResponse<>(false,  "Autor informado não localizado para cadastro do livro ", null));
        } else {
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setGenero(genero);
        livro.setAno(ano);
        livro.setAutor(autor);
        livroRepository.save(livro);
        return ResponseEntity.ok(new ApiResponse<>(true,  "Livro cadastrado com sucesso! ", livro));
        }
    }

    public Livro consultaLivro(String id, String titulo) {
        if (id != null && !id.isBlank()) {
            return livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        }
        if (titulo != null && !titulo.isBlank()) {
            return livroRepository.findByTitulo(titulo).orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        }
        throw new RuntimeException("Necessário informar ao menos um parâmetro para consulta");
    }

    public ResponseEntity<ApiResponse<List<Livro>>> listaLivros(){
        List<Livro> livros = livroRepository.findAll();
        if (!livros.isEmpty()){
            return ResponseEntity.ok(new ApiResponse<>(true,  "Lista de Livros:", livros));
        } else {
            return ResponseEntity.ok(new ApiResponse<>(false,  "Não existe livros cadastrados no sistema!", null));
        }
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
}
