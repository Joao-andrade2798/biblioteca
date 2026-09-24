package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.dto.ApiResponse;
import com.andrade.bibliotecaApi.dto.AutorDTO;
import com.andrade.bibliotecaApi.entity.Autor;
import com.andrade.bibliotecaApi.service.AutorService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/biblioteca/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<Autor>> cadastroAutor(@Valid @RequestBody AutorDTO autorDto ) {
        try {
            Autor autor = autorService.cadastrarAutor(autorDto.getNome(), autorDto.getNacionalidade());
            ApiResponse<Autor> resposta = new ApiResponse<>(true,"Autor cadastrado com sucesso!",autor);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);

        } catch (Exception e) {
            ApiResponse<Autor> erro = new ApiResponse<>(false,"Erro ao cadastrar autor: " + e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
        }
    }

    @GetMapping("/listagem")
    public ResponseEntity<ApiResponse<List<Autor>>> listarAutores() {
            List<Autor> autores = autorService.listarAutores();
        return ResponseEntity.ok(new ApiResponse<>(true, "Lista de Autores: ", autores));
    }

    @GetMapping("/consulta")
    public ResponseEntity<ApiResponse<Autor>> consultaAutor(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String nome
    ) {
        Autor autores = autorService.consultaAutor(id, nome);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lista de Autores: ", autores));
    }

    @DeleteMapping("/exclusao")
    public ResponseEntity<ApiResponse<AutorDTO>> excluirAutor
            (@Valid
             @RequestParam(required = false) String id,
             @RequestParam(required = false) String nome) {

        AutorDTO autores = autorService.excluirAutor(id, nome);
        return ResponseEntity.ok(new ApiResponse<>(true, "Autor excluído com sucesso", autores));

    }
}
