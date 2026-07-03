package com.andrade.bibliotecaApi.controller;

import com.andrade.bibliotecaApi.dto.ApiResponse;
import com.andrade.bibliotecaApi.dto.AutorDTO;
import com.andrade.bibliotecaApi.dto.UsuarioDTO;
import com.andrade.bibliotecaApi.entity.Autor;
import com.andrade.bibliotecaApi.entity.Usuario;
import com.andrade.bibliotecaApi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/biblioteca/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<Usuario>> cadastroUsuario(@Valid @RequestBody UsuarioDTO usuarioDto) {
        try {
            Usuario usuario = usuarioService.cadastrarUsuario(usuarioDto.getNome(), usuarioDto.getEmail(), usuarioDto.getTelefone());
            ApiResponse<Usuario> resposta = new ApiResponse<>(true, "Usuário cadastrado com sucesso!", usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (Exception e) {
            ApiResponse<Usuario> erro = new ApiResponse<>(false, "Erro ao cadastrar usuário: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
        }
    }

    @GetMapping("/listagem")
    public ResponseEntity<ApiResponse<List<Usuario>>> listagemUsuario() {
        List<Usuario> usuario = usuarioService.listagemUsuario();

        return ResponseEntity.ok(new ApiResponse<>(true, "Lista de usuários:", usuario));
    }

    @GetMapping("/consulta")
    public ResponseEntity<ApiResponse<Usuario>> consultaUsuario(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String nome) {

    Usuario usuario = usuarioService.consultaUsuario(id, nome);
    return ResponseEntity.ok(new ApiResponse<>(true, "Lista de usuários:", usuario));
    }

    @DeleteMapping("/exclusao")
    public ResponseEntity<ApiResponse<UsuarioDTO>> excluirUsuario(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String nome
    ) {
        try {
            UsuarioDTO usuario = usuarioService.excluirUsuario(id, nome);
            return ResponseEntity.ok(new ApiResponse<>(true, "Usuario excluído com sucesso:", usuario));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse<>(false, "Usuario informado não localizado", null));
        }
    }
}
