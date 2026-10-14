package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.dto.response.ApiResponse;
import com.andrade.bibliotecaApi.dto.response.EmprestimoDTO;
import com.andrade.bibliotecaApi.service.EmprestimoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
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

    @PostMapping("/devolucao")
    public ResponseEntity<ApiResponse<List<EmprestimoDTO>>> devolucaoExemplar (@Valid @RequestBody EmprestimoDTO request) {
        List<EmprestimoDTO> emprestimoDTOS = emprestimoService.devolucao(request);

        return ResponseEntity.ok(new ApiResponse<>(true, "Devolução feita com sucesso", emprestimoDTOS));
    }

}
