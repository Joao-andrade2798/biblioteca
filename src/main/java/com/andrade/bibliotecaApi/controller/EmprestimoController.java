package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.dto.response.ApiResponse;
import com.andrade.bibliotecaApi.dto.response.EmprestimoDTO;
import com.andrade.bibliotecaApi.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/biblioteca/emprestimo")
public class EmprestimoController {
    @Autowired
    EmprestimoService emprestimoService;

    @GetMapping("/listagem")
    public ResponseEntity<ApiResponse<List<EmprestimoDTO>>> listagemEmprestimos () {
        List<EmprestimoDTO> retorno = emprestimoService.consultaEmprestimos();

        return ResponseEntity.ok(new ApiResponse<>(true, "Emprestimos retornados com sucesso", retorno));
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
