package com.andrade.bibliotecaApi.service;

import com.andrade.bibliotecaApi.dto.response.EmprestimoDTO;
import com.andrade.bibliotecaApi.entity.Emprestimo;
import com.andrade.bibliotecaApi.entity.Exemplar;
import com.andrade.bibliotecaApi.entity.Usuario;
import com.andrade.bibliotecaApi.enums.StatusExemplarEnum;
import com.andrade.bibliotecaApi.exceptions.RecursoNaoEncontradoException;
import com.andrade.bibliotecaApi.repository.EmprestimoRepository;
import com.andrade.bibliotecaApi.repository.ExemplarRepository;
import com.andrade.bibliotecaApi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    EmprestimoRepository emprestimoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ExemplarRepository exemplarRepository;

    public List<EmprestimoDTO> consultaEmprestimos() {
        return emprestimoRepository.buscarTodosEmprestimos().stream()
                .map(e -> new EmprestimoDTO(
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
    return new EmprestimoDTO(emprestimo.getUsuario().getId(), emprestimo.getExemplar().getLivro().getId(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }

    /**
     * @param dto dto contendo dados do emprestimo
     * @return em caso de sucesso retorna os exemplares que foram devolvidos
     */
    public List<EmprestimoDTO> devolucao(EmprestimoDTO dto) {
        List<Emprestimo> emprestimos = emprestimoRepository.consultaEmprestimo(dto.idUsuario(), dto.idLivro());
        if (emprestimos.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum empréstimo ativo encontrado");
        }
        LocalDate hoje = LocalDate.now();
        List<Exemplar> exemplares = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {
            emprestimo.setDataDevolucao(hoje);
            exemplares.add(emprestimo.getExemplar());
        }

        for (Exemplar exemplar : exemplares) {
         exemplar.setStatus(StatusExemplarEnum.DISPONIVEL);
        }

        emprestimoRepository.saveAll(emprestimos);
        exemplarRepository.saveAll(exemplares);

        return emprestimos
                .stream()
                .map(e -> new EmprestimoDTO(e.getUsuario().getId(), e.getExemplar().getLivro().getId(), e.getDataEmprestimo(), e.getDataDevolucao()))
                .toList();
    }
}
