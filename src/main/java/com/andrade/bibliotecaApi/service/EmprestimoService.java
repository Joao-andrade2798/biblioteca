package com.andrade.bibliotecaApi.service;

import com.andrade.bibliotecaApi.dto.response.EmprestimoDTO;
import com.andrade.bibliotecaApi.entity.Emprestimo;
import com.andrade.bibliotecaApi.entity.Exemplar;
import com.andrade.bibliotecaApi.entity.Livro;
import com.andrade.bibliotecaApi.entity.Usuario;
import com.andrade.bibliotecaApi.enums.StatusExemplarEnum;
import com.andrade.bibliotecaApi.repository.AutorRepository;
import com.andrade.bibliotecaApi.repository.EmprestimoRepository;
import com.andrade.bibliotecaApi.repository.ExemplarRepository;
import com.andrade.bibliotecaApi.repository.LivroRepository;
import com.andrade.bibliotecaApi.repository.UsuarioRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
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
    ExemplarRepository exemplarRepository;

    /**
    * Registra um empréstimo para o usuário e exemplar informado
    * <br>
    * e altera situação do exemplar encontrado para Emprestado
    *
    * @param dto dados do usuário e exemplar
    * @return o empréstimo criado
    */
    @Transactional
    public EmprestimoDTO registro(EmprestimoDTO dto) {
    Usuario usuario = usuarioRepository.findById(dto.idUsuario())
            .orElseThrow(() -> new NoResultException("Usuário não encontrado para registro do empréstimo"));

    Exemplar exemplar = exemplarRepository.findByLivroId(dto.idLivro())
            .stream()
            .filter(e -> e.getStatus().equals(StatusExemplarEnum.DISPONIVEL))
            .findFirst()
            .orElseThrow(() -> new NoResultException("Exemplar não encontrado para registro do empréstimo"));

    Emprestimo emprestimo = emprestimoRepository.save(new Emprestimo(usuario, exemplar, LocalDate.now(), null));
    exemplar.setStatus(StatusExemplarEnum.EMPRESTADO);
    return new EmprestimoDTO(emprestimo.getUsuario().getId(), emprestimo.getExemplar().getLivro().getId(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }
}
