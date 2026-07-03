package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/biblioteca/emprestimo")
public class EmprestimoController {
    @Autowired
    EmprestimoService emprestimoService;


}
