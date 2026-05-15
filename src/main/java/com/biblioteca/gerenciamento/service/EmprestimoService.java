package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.config.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.domain.dto.CondicaoLivro;
import com.biblioteca.gerenciamento.domain.dto.StatusEmprestimo;
import com.biblioteca.gerenciamento.domain.entity.Aluno;
import com.biblioteca.gerenciamento.domain.entity.Emprestimo;
import com.biblioteca.gerenciamento.domain.entity.Estoque;
import com.biblioteca.gerenciamento.domain.entity.Livro;
import com.biblioteca.gerenciamento.repository.EmprestimoRepository;
import com.biblioteca.gerenciamento.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final EstoqueRepository estoqueRepository;
    private final LivroService livroService;
    private final AlunoService alunoService;

    public Emprestimo create(Emprestimo emprestimoRequest) throws BadRequestException {

        Livro livro = livroService.findById(
                emprestimoRequest.getLivro().getId()
        );

        Aluno aluno = alunoService.findById(
                emprestimoRequest.getAluno().getId()
        );

        Estoque estoque = estoqueRepository.findByLivroId(livro.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Estoque não encontrado"));

        if (estoque.getQtdDisponivel() <= 0) {
            throw new BadRequestException(
                    "Não existe quantidade disponível para empréstimo");
        }

        CondicaoLivro condicao = emprestimoRequest.getCondicaoEntrega();

        diminuirQuantidadePorCondicao(estoque, condicao);

        estoque.setQtdDisponivel(
                estoque.getQtdDisponivel() - 1
        );

        estoqueRepository.save(estoque);

        Emprestimo emprestimo = Emprestimo.builder()
                .dataEntrega(LocalDate.now())
                .statusEmprestimo(StatusEmprestimo.EMPRESTADO)
                .condicaoEntrega(condicao)
                .aluno(aluno)
                .livro(livro)
                .build();

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo findById(Integer id) {

        return emprestimoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Empréstimo não encontrado"));
    }

    public List<Emprestimo> findAll(
            String status,
            String nomeAluno,
            String tituloLivro
    ) {

        return emprestimoRepository.findByFiltros(
                status,
                nomeAluno,
                tituloLivro
        );
    }

    public Emprestimo devolver(
            Integer id,
            CondicaoLivro condicaoDevolucao
    ) throws BadRequestException {

        Emprestimo emprestimo = findById(id);

        if (StatusEmprestimo.DEVOLVIDO.equals(
                emprestimo.getStatusEmprestimo())) {

            throw new BadRequestException(
                    "Livro já foi devolvido");
        }

        Estoque estoque = estoqueRepository.findByLivroId(
                emprestimo.getLivro().getId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Estoque não encontrado"));

        estoque.setQtdDisponivel(
                estoque.getQtdDisponivel() + 1
        );

        aumentarQuantidadePorCondicao(
                estoque,
                condicaoDevolucao
        );

        estoqueRepository.save(estoque);

        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.setCondicaoDevolucao(condicaoDevolucao);
        emprestimo.setStatusEmprestimo(StatusEmprestimo.DEVOLVIDO);

        return emprestimoRepository.save(emprestimo);
    }

    private void diminuirQuantidadePorCondicao(
            Estoque estoque,
            CondicaoLivro condicao
    ) throws BadRequestException {

        switch (condicao) {

            case NOVO -> {
                if (estoque.getQtdNovo() <= 0) {
                    throw new BadRequestException(
                            "Não há livros NOVOS disponíveis");
                }

                estoque.setQtdNovo(
                        estoque.getQtdNovo() - 1
                );
            }

            case CONSERVADO -> {
                if (estoque.getQtdConservado() <= 0) {
                    throw new BadRequestException(
                            "Não há livros CONSERVADOS disponíveis");
                }

                estoque.setQtdConservado(
                        estoque.getQtdConservado() - 1
                );
            }

            case MAL_CONSERVADO -> {
                if (estoque.getQtdMalConservado() <= 0) {
                    throw new BadRequestException(
                            "Não há livros MAL_CONSERVADOS disponíveis");
                }

                estoque.setQtdMalConservado(
                        estoque.getQtdMalConservado() - 1
                );
            }

            case INUTILIZADO -> {
                if (estoque.getQtdInutilizado() <= 0) {
                    throw new BadRequestException(
                            "Não há livros INUTILIZADOS disponíveis");
                }

                estoque.setQtdInutilizado(
                        estoque.getQtdInutilizado() - 1
                );
            }
        }
    }

    private void aumentarQuantidadePorCondicao(
            Estoque estoque,
            CondicaoLivro condicao
    ) {

        switch (condicao) {

            case NOVO -> estoque.setQtdNovo(
                    estoque.getQtdNovo() + 1
            );

            case CONSERVADO -> estoque.setQtdConservado(
                    estoque.getQtdConservado() + 1
            );

            case MAL_CONSERVADO -> estoque.setQtdMalConservado(
                    estoque.getQtdMalConservado() + 1
            );

            case INUTILIZADO -> estoque.setQtdInutilizado(
                    estoque.getQtdInutilizado() + 1
            );
        }
    }

    public void delete(Integer id) {

        Emprestimo emprestimo = findById(id);

        if (StatusEmprestimo.EMPRESTADO.equals(
                emprestimo.getStatusEmprestimo())) {

            Estoque estoque = estoqueRepository.findByLivroId(
                    emprestimo.getLivro().getId()
            ).orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Estoque não encontrado"
                    ));

            estoque.setQtdDisponivel(
                    estoque.getQtdDisponivel() + 1
            );

            aumentarQuantidadePorCondicao(
                    estoque,
                    emprestimo.getCondicaoEntrega()
            );

            estoqueRepository.save(estoque);
        }

        emprestimoRepository.delete(emprestimo);
    }
}