package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.dto.ApiResponse;
import com.andrade.bibliotecaApi.dto.LivroDTO;
import com.andrade.bibliotecaApi.entity.Autor;
import com.andrade.bibliotecaApi.entity.Livro;
import com.andrade.bibliotecaApi.service.LivroService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/biblioteca/livro")
public class LivroController {

    @Autowired
    LivroService livroService;

    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<Livro>> cadastrarLivro(@Valid @RequestBody LivroDTO livroDTO) {
        return livroService.cadastrarLivro(livroDTO.getTitulo(), livroDTO.getAno(), livroDTO.getGenero(), livroDTO.getAutor().getNome());
    }

    @GetMapping("/consulta")
    public ResponseEntity<ApiResponse<Livro>> consultaLivro(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String titulo
    ) {
        try {
            Livro livros = livroService.consultaLivro(id, titulo);
            return ResponseEntity.ok(new ApiResponse<>(true, "Livro encontrado: ", livros));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse<>(false, "Livro informado não localizado!", null));
        }
    }

    @GetMapping("/listagem")
    public ResponseEntity<ApiResponse<List<Livro>>> listagemLivros() {
        return livroService.listaLivros();
    }

    @DeleteMapping("/exclusao")
    public ResponseEntity<ApiResponse<LivroDTO>> exclusaoLivros
            (@Valid @RequestParam(required = false) String id,
             @RequestParam(required = false) String titulo) {
        try {
            LivroDTO livros = livroService.excluirLivros(id, titulo);
            return ResponseEntity.ok(new ApiResponse<>(true, "Livro excluído com sucesso", livros));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse<>(false, "Livro informado não localizado!", null));
        }
    }
}
