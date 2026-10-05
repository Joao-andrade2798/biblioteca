package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.dto.response.ApiResponse;
import com.andrade.bibliotecaApi.dto.response.EmprestimoDTO;
import com.andrade.bibliotecaApi.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/biblioteca/emprestimo")
public class EmprestimoController {
    @Autowired
    EmprestimoService emprestimoService;

    @PostMapping("/registro")
    public ResponseEntity<ApiResponse<EmprestimoDTO>> registroEmprestimo (@Valid @RequestBody EmprestimoDTO request) {
        EmprestimoDTO emprestimoDTO = emprestimoService.registro(request);

        return ResponseEntity.ok(new ApiResponse<>(true, "Empréstimo registrado com sucesso", emprestimoDTO));
    }

}
