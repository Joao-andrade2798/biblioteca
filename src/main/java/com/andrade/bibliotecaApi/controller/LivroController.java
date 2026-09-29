package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.dto.request.ExemplarRequest;
import com.andrade.bibliotecaApi.dto.response.ApiResponse;
import com.andrade.bibliotecaApi.dto.response.ExemplarDTO;
import com.andrade.bibliotecaApi.dto.response.LivroDTO;
import com.andrade.bibliotecaApi.service.LivroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
        return livroService.cadastrarLivro(livroDTO.titulo(), livroDTO.ano(), livroDTO.genero(), livroDTO.autor().nome());
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

    @Valid
    @PostMapping("/cadastro/exemplar")
    public ResponseEntity<ApiResponse<List<ExemplarDTO>>> cadastroExemplares(ExemplarRequest request) {
        List<ExemplarDTO> exemplarDTO = livroService.cadastroExemplar(request);

        return ResponseEntity.ok(new ApiResponse<>(true, "Exemplares cadastrados com sucesso", exemplarDTO));
    }
}
