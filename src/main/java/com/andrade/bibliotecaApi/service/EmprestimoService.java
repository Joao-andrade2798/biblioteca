package com.andrade.bibliotecaApi.service;

import com.andrade.bibliotecaApi.dto.response.EmprestimoDTO;
import com.andrade.bibliotecaApi.entity.Emprestimo;
import com.andrade.bibliotecaApi.entity.Livro;
import com.andrade.bibliotecaApi.entity.Usuario;
import com.andrade.bibliotecaApi.repository.AutorRepository;
import com.andrade.bibliotecaApi.repository.EmprestimoRepository;
import com.andrade.bibliotecaApi.repository.LivroRepository;
import com.andrade.bibliotecaApi.repository.UsuarioRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmprestimoService {
    @Autowired
    EmprestimoRepository emprestimoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    LivroRepository livroRepository;


    public EmprestimoDTO registro(EmprestimoDTO dto) {
    Usuario usuario = usuarioRepository.findById(dto.idUsuario())
            .orElseThrow(() -> new NoResultException("Usuário não encontrado para registro do empréstimo"));

    Livro livro = livroRepository.findById(dto.idLivro())
            .orElseThrow(() -> new NoResultException("Livro não encontrado para registro do empréstimo"));

    Emprestimo emprestimo = emprestimoRepository.save(new Emprestimo(usuario, livro, LocalDate.now(), null));
    emprestimoRepository.
    return dto = new EmprestimoDTO(emprestimo.getId(), emprestimo.getLivro().getId(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }
}
