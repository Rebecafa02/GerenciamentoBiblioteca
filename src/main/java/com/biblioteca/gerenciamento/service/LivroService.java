package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.config.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.domain.entity.Livro;
import com.biblioteca.gerenciamento.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public Livro create(Livro livro) {

        return livroRepository.save(livro);
    }

    public Livro findById(Integer id) {

        return livroRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Livro não encontrado"));
    }

    public List<Livro> findAll(
            String titulo,
            String disciplina,
            String anoEscolar,
            String edicao
    ) {

        titulo = titulo == null ? "" : titulo;
        disciplina = disciplina == null ? "" : disciplina;
        anoEscolar = anoEscolar == null ? "" : anoEscolar;
        edicao = edicao == null ? "" : edicao;

        return livroRepository.findByFiltros(
                titulo,
                disciplina,
                anoEscolar,
                edicao
        );
    }

    public Livro updateParcial(Integer id, Livro livroRequest) {

        Livro livro = findById(id);

        if (livroRequest.getTitulo() != null) {
            livro.setTitulo(livroRequest.getTitulo());
        }

        if (livroRequest.getDisciplina() != null) {
            livro.setDisciplina(livroRequest.getDisciplina());
        }

        if (livroRequest.getAnoEscolar() != null) {
            livro.setAnoEscolar(livroRequest.getAnoEscolar());
        }

        if (livroRequest.getEdicao() != null) {
            livro.setEdicao(livroRequest.getEdicao());
        }

        return livroRepository.save(livro);
    }

    public void delete(Integer id) {

        Livro livro = findById(id);

        livroRepository.delete(livro);
    }
}