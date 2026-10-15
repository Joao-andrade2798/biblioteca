package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.dto.response.ApiResponse;
import com.andrade.bibliotecaApi.dto.response.EmprestimoDTO;
import com.andrade.bibliotecaApi.service.EmprestimoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Empréstimo", description = "Registro e devolução de empréstimos")
@RestController
@RequestMapping("/biblioteca/emprestimo")
@Validated
public class EmprestimoController {
    @Autowired
    EmprestimoService emprestimoService;

    @GetMapping("/listagem")
    public ResponseEntity<ApiResponse<List<EmprestimoDTO>>> listagemEmprestimos () {
        List<EmprestimoDTO> retorno = emprestimoService.consultarTodosEmprestimos();

        return ResponseEntity.ok(new ApiResponse<>(true, "Emprestimos retornados com sucesso", retorno));
    }

    @GetMapping("/consultaPorUsuario")
    public ResponseEntity<ApiResponse<List<EmprestimoDTO>>> consultaEmprestimosPorUsuario(
            @RequestParam @NotBlank(message = "Parâmetro idUsuario é obrigatório") String idUsuario) {
        List<EmprestimoDTO> retorno = emprestimoService.consultarEmprestimosPorUsuario(idUsuario);

        return ResponseEntity.ok(new ApiResponse<>(true, "Lista de emprestimos do usuário: ", retorno));
    }


    @PostMapping("/registro")
    public ResponseEntity<ApiResponse<EmprestimoDTO>> registroEmprestimo (@Valid @RequestBody EmprestimoDTO request) {
        EmprestimoDTO emprestimoDTO = emprestimoService.registro(request);

        return ResponseEntity.ok(new ApiResponse<>(true, "Empréstimo registrado com sucesso", emprestimoDTO));
    }

    @PatchMapping("/{id}/devolucao")
    public ResponseEntity<ApiResponse<EmprestimoDTO>> devolucaoExemplar (@Valid @PathVariable String id) {
        EmprestimoDTO emprestimos = emprestimoService.devolucao(id);

        return ResponseEntity.ok(new ApiResponse<>(true, "Devolução feita com sucesso", emprestimos));
    }

}
