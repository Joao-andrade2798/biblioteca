package com.andrade.bibliotecaApi.service;

import com.andrade.bibliotecaApi.dto.AutorDTO;
import com.andrade.bibliotecaApi.entity.Autor;
import com.andrade.bibliotecaApi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor cadastrarAutor(String nome, String nacionalidade) {
        Autor autor = new Autor(nome, nacionalidade);
        return autorRepository.save(autor);
    }

    public List<Autor> listarAutores(){
        return autorRepository.findAll();
    }

    public Autor consultaAutor(String id, String nome) {
        if (id != null && !id.isBlank()) {
            return autorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        }
        if (nome != null && !nome.isBlank()) {
            return autorRepository.findByNome(nome)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        }
        throw new RuntimeException("Necessário informar ao menos um parâmetro para consulta");

    }

    public AutorDTO excluirAutor(String id, String nome) {
        Autor autor = null;

        if (id != null && !id.isBlank()) {
            autor = autorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        } else if (nome != null && !nome.isBlank()) {
            autor = autorRepository.findByNome(nome)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        } else {
            throw new RuntimeException("Necessário informar ao menos um parâmetro para exclusão");
        }

        autorRepository.delete(autor);
        return new AutorDTO(autor.getNome(), autor.getNacionalidade());
    }

}
