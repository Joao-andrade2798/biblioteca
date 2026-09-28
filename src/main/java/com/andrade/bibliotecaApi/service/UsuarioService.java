package com.andrade.bibliotecaApi.service;

import com.andrade.bibliotecaApi.dto.response.UsuarioDTO;
import com.andrade.bibliotecaApi.entity.Usuario;
import com.andrade.bibliotecaApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(String nome, String email, String telefone){

        Usuario usuario = new Usuario(nome, email, telefone);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listagemUsuario(){
        return usuarioRepository.findAll();
    }


    public Usuario consultaUsuario(String id, String nome) {
        if (id != null && !id.isBlank()) {
            return usuarioRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        }

        if (nome != null && !nome.isBlank()) {
            return usuarioRepository.findByNome(nome)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "É necessário informar algum parâmetro para consulta!");
    }


    public UsuarioDTO excluirUsuario(String id, String nome) {
        Usuario usuario = null;
        if (id != null && !id.isBlank()) {
            usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        }
        if (nome != null && !nome.isBlank()) {
            usuario = usuarioRepository.findByNome(nome)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        } else {
            throw new RuntimeException("É necessário informar algum parâmetro para consulta!");
        }
        usuarioRepository.delete(usuario);
        return new UsuarioDTO(usuario.getNome(), usuario.getEmail(), usuario.getTelefone());
    }
}
