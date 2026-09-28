package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.dto.response.ApiResponse;
import com.andrade.bibliotecaApi.dto.response.ExemplarDTO;
import com.andrade.bibliotecaApi.dto.response.LivroDTO;
import com.andrade.bibliotecaApi.service.LivroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
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
            LivroDTO livros = livroService.excluirLivros(id, titulo);

            return ResponseEntity.ok(new ApiResponse<>(true, "Livro excluído com sucesso", livros));
    }

    @Validated
    @PostMapping("/cadastro/exemplar")
    public ResponseEntity<ApiResponse<List<ExemplarDTO>>> cadastroExemplares(
            @Valid @RequestBody String idLivro,
            @RequestParam @Min(1) Integer quantidade) {
        List<ExemplarDTO> exemplarDTO = livroService.cadastroExemplar(idLivro, quantidade);

        return ResponseEntity.ok(new ApiResponse<>(true, "Exemplares cadastrados com sucesso", exemplarDTO));
    }
}
