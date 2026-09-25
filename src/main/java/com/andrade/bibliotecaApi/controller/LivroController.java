package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.dto.ApiResponse;
import com.andrade.bibliotecaApi.dto.ExemplarDTO;
import com.andrade.bibliotecaApi.dto.LivroDTO;
import com.andrade.bibliotecaApi.entity.Autor;
import com.andrade.bibliotecaApi.entity.Livro;
import com.andrade.bibliotecaApi.service.LivroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/biblioteca/livro")
public class LivroController {

    @Autowired
    LivroService livroService;

    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<LivroDTO>> cadastrarLivro(@Valid @RequestBody LivroDTO livroDTO) {
        return livroService.cadastrarLivro(livroDTO.getTitulo(), livroDTO.getAno(), livroDTO.getGenero(), livroDTO.getAutor().getNome());
    }

    @GetMapping("/consulta")
    public ResponseEntity<ApiResponse<LivroDTO>> consultaLivro(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String titulo
    ) {
            LivroDTO livros = livroService.consultaLivro(id, titulo);
            return ResponseEntity.ok(new ApiResponse<>(true, "Livro encontrado: ", livros));
    }

    @GetMapping("/listagem")
    public ResponseEntity<ApiResponse<List<LivroDTO>>> listagemLivros() {
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

    @Validated
    @PostMapping("/cadastro/exemplar")
    public ResponseEntity<ApiResponse<List<ExemplarDTO>>> cadastroExemplares(
            @Valid @RequestBody ExemplarDTO exemplar,
            @RequestParam @Min(1) Integer quantidade) {

    }
}
