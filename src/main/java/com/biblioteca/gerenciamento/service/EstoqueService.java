package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.config.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.domain.entity.Estoque;
import com.biblioteca.gerenciamento.domain.entity.Livro;
import com.biblioteca.gerenciamento.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final LivroService livroService;

    public Estoque create(Estoque estoqueRequest) throws BadRequestException {

        Livro livro = livroService.findById(
                estoqueRequest.getLivro().getId()
        );

        boolean estoqueExiste = estoqueRepository
                .findByLivroId(livro.getId())
                .isPresent();

        if (estoqueExiste) {
            throw new BadRequestException(
                    "Já existe estoque para este livro"
            );
        }

        Estoque estoque = Estoque.builder()
                .livro(livro)
                .qtdTotal(estoqueRequest.getQtdTotal())
                .qtdDisponivel(estoqueRequest.getQtdDisponivel())
                .qtdNovo(estoqueRequest.getQtdNovo())
                .qtdConservado(estoqueRequest.getQtdConservado())
                .qtdMalConservado(estoqueRequest.getQtdMalConservado())
                .qtdInutilizado(estoqueRequest.getQtdInutilizado())
                .build();

        validarQuantidades(estoque);

        return estoqueRepository.save(estoque);
    }

    public List<Estoque> findAll() {
        return estoqueRepository.findAll();
    }

    public Estoque findById(Integer id) {

        return estoqueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Estoque não encontrado"
                        ));
    }

    public Estoque update(
            Integer id,
            Estoque estoqueRequest
    ) throws BadRequestException {

        Estoque estoque = findById(id);

        if (estoqueRequest.getLivro() != null &&
                estoqueRequest.getLivro().getId() != null) {

            Livro livro = livroService.findById(
                    estoqueRequest.getLivro().getId()
            );

            estoque.setLivro(livro);
        }

        estoque.setQtdTotal(estoqueRequest.getQtdTotal());
        estoque.setQtdDisponivel(
                estoqueRequest.getQtdDisponivel()
        );
        estoque.setQtdNovo(estoqueRequest.getQtdNovo());
        estoque.setQtdConservado(
                estoqueRequest.getQtdConservado()
        );
        estoque.setQtdMalConservado(
                estoqueRequest.getQtdMalConservado()
        );
        estoque.setQtdInutilizado(
                estoqueRequest.getQtdInutilizado()
        );

        validarQuantidades(estoque);

        return estoqueRepository.save(estoque);
    }

    public Estoque updateParcial(
            Integer id,
            Estoque estoqueRequest
    ) throws BadRequestException {

        Estoque estoque = findById(id);

        if (estoqueRequest.getLivro() != null &&
                estoqueRequest.getLivro().getId() != null) {

            Livro livro = livroService.findById(
                    estoqueRequest.getLivro().getId()
            );

            estoque.setLivro(livro);
        }

        if (estoqueRequest.getQtdTotal() != null) {
            estoque.setQtdTotal(
                    estoqueRequest.getQtdTotal()
            );
        }

        if (estoqueRequest.getQtdDisponivel() != null) {
            estoque.setQtdDisponivel(
                    estoqueRequest.getQtdDisponivel()
            );
        }

        if (estoqueRequest.getQtdNovo() != null) {
            estoque.setQtdNovo(
                    estoqueRequest.getQtdNovo()
            );
        }

        if (estoqueRequest.getQtdConservado() != null) {
            estoque.setQtdConservado(
                    estoqueRequest.getQtdConservado()
            );
        }

        if (estoqueRequest.getQtdMalConservado() != null) {
            estoque.setQtdMalConservado(
                    estoqueRequest.getQtdMalConservado()
            );
        }

        if (estoqueRequest.getQtdInutilizado() != null) {
            estoque.setQtdInutilizado(
                    estoqueRequest.getQtdInutilizado()
            );
        }

        validarQuantidades(estoque);

        return estoqueRepository.save(estoque);
    }

    public void delete(Integer id) {

        Estoque estoque = findById(id);

        estoqueRepository.delete(estoque);
    }

    private void validarQuantidades(Estoque estoque) throws BadRequestException {

        int somaCondicoes =
                estoque.getQtdNovo()
                        + estoque.getQtdConservado()
                        + estoque.getQtdMalConservado()
                        + estoque.getQtdInutilizado();

        if (somaCondicoes != estoque.getQtdTotal()) {

            throw new BadRequestException(
                    "A soma das condições deve ser igual à quantidade total"
            );
        }

        if (estoque.getQtdDisponivel() > estoque.getQtdTotal()) {

            throw new BadRequestException(
                    "Quantidade disponível não pode ser maior que a total"
            );
        }
    }
}