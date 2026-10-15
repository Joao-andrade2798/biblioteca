package com.andrade.bibliotecaApi.service;

import com.andrade.bibliotecaApi.dto.response.EmprestimoDTO;
import com.andrade.bibliotecaApi.entity.Emprestimo;
import com.andrade.bibliotecaApi.entity.Exemplar;
import com.andrade.bibliotecaApi.entity.Usuario;
import com.andrade.bibliotecaApi.enums.StatusExemplarEnum;
import com.andrade.bibliotecaApi.exceptions.RecursoNaoEncontradoException;
import com.andrade.bibliotecaApi.exceptions.UnprocessableException;
import com.andrade.bibliotecaApi.repository.EmprestimoRepository;
import com.andrade.bibliotecaApi.repository.ExemplarRepository;
import com.andrade.bibliotecaApi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    EmprestimoRepository emprestimoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ExemplarRepository exemplarRepository;

    public List<EmprestimoDTO> consultarTodosEmprestimos() {
        return emprestimoRepository.buscarTodosEmprestimos().stream()
                .map(e -> new EmprestimoDTO(
                        e.getId(),
                        e.getUsuario().getId(),
                        e.getExemplar().getLivro().getId(),
                        e.getDataEmprestimo(),
                        e.getDataDevolucao()))
                .toList();
    }

    public List<EmprestimoDTO> consultarEmprestimosPorUsuario(String idUsuario) {
        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário informado não encontrado"));

        return emprestimoRepository.buscarEmprestimosAtivosPorUsuario(idUsuario).stream()
                .map(e -> new EmprestimoDTO(
                        e.getId(),
                        e.getUsuario().getId(),
                        e.getExemplar().getLivro().getId(),
                        e.getDataEmprestimo(),
                        e.getDataDevolucao()))
                .toList();
    }

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
            .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado para registro do empréstimo"));

    Exemplar exemplar = exemplarRepository.findByLivroId(dto.idLivro())
            .stream()
            .filter(e -> e.getStatus().equals(StatusExemplarEnum.DISPONIVEL))
            .findFirst()
            .orElseThrow(() -> new RecursoNaoEncontradoException("Não encontrado exemplares para registro do empréstimo"));

    Emprestimo emprestimo = emprestimoRepository.save(new Emprestimo(usuario, exemplar, LocalDate.now(), null));
    exemplar.setStatus(StatusExemplarEnum.EMPRESTADO);
    return new EmprestimoDTO(emprestimo.getId(), emprestimo.getUsuario().getId(), emprestimo.getExemplar().getLivro().getId(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }

    /**
     * @param id Refere-se ao id do empréstimo
     * @return em caso de sucesso retorna o exemplar que foi devolvido
     */
    @Transactional
    public EmprestimoDTO devolucao(String id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Nenhum empréstimo ativo encontrado"));

        if (emprestimo.getDataDevolucao() != null) {
            throw new UnprocessableException("Empréstimo já foi devolvido");
        }

        LocalDate hoje = LocalDate.now();

        emprestimo.setDataDevolucao(hoje);
        emprestimo.getExemplar().setStatus(StatusExemplarEnum.DISPONIVEL);

        return new EmprestimoDTO(emprestimo.getId(), emprestimo.getUsuario().getId(), emprestimo.getExemplar().getLivro().getId(), emprestimo.getDataEmprestimo(), hoje);
    }
}
