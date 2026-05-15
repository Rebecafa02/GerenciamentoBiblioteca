package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.config.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.domain.entity.Aluno;
import com.biblioteca.gerenciamento.domain.entity.Turma;
import com.biblioteca.gerenciamento.repository.AlunoRepository;
import com.biblioteca.gerenciamento.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final TurmaService turmaService;

    public Aluno create(Aluno aluno) throws BadRequestException {

        validarMatriculaDuplicada(aluno.getMatricula());

        if (aluno.getTurma() != null &&
                aluno.getTurma().getId() != null) {

            aluno.setTurma(
                    turmaService.findById(aluno.getTurma().getId())
            );
        }

        return alunoRepository.save(aluno);
    }

    public Aluno findById(Integer id) {

        return alunoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Aluno não encontrado"));
    }

    public List<Aluno> findAll(
            String nome,
            String email,
            String matricula,
            Integer turmaId
    ) {

        nome = nome == null ? "" : nome;
        email = email == null ? "" : email;
        matricula = matricula == null ? "" : matricula;

        return alunoRepository.findByFiltros(
                nome,
                email,
                matricula,
                turmaId
        );
    }

    public Aluno updateParcial(Integer id, Aluno alunoRequest) throws BadRequestException {

        Aluno aluno = findById(id);

        if (alunoRequest.getNome() != null) {
            aluno.setNome(alunoRequest.getNome());
        }

        if (alunoRequest.getEmail() != null) {
            aluno.setEmail(alunoRequest.getEmail());
        }

        if (alunoRequest.getFotoPerfil() != null) {
            aluno.setFotoPerfil(alunoRequest.getFotoPerfil());
        }

        if (alunoRequest.getMatricula() != null &&
                !alunoRequest.getMatricula().equals(aluno.getMatricula())) {

            validarMatriculaDuplicada(alunoRequest.getMatricula());

            aluno.setMatricula(alunoRequest.getMatricula());
        }

        if (alunoRequest.getTurma() != null &&
                alunoRequest.getTurma().getId() != null) {

            aluno.setTurma(
                    turmaService.findById(alunoRequest.getTurma().getId())
            );
        }

        return alunoRepository.save(aluno);
    }

    public void delete(Integer id) {

        Aluno aluno = findById(id);

        alunoRepository.delete(aluno);
    }

    private void validarMatriculaDuplicada(String matricula) throws BadRequestException {

        if (alunoRepository.findByMatricula(matricula).isPresent()) {
            throw new BadRequestException("Matrícula já cadastrada");
        }
    }
}