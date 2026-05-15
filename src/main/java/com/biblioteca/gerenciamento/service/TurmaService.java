package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.domain.dto.TurmaCreateDto;
import com.biblioteca.gerenciamento.domain.dto.TurmaUpdateDto;
import com.biblioteca.gerenciamento.domain.entity.Campus;
import com.biblioteca.gerenciamento.domain.entity.Curso;
import com.biblioteca.gerenciamento.domain.entity.Turma;
import com.biblioteca.gerenciamento.repository.CampusRepository;
import com.biblioteca.gerenciamento.repository.CursoRepository;
import com.biblioteca.gerenciamento.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final CursoRepository cursoRepository;
    private final CampusRepository campusRepository;

    public Turma findById(Integer id) {

        return turmaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Turma não encontrada"));
    }

    public Turma create(TurmaCreateDto dto) {

        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() ->
                        new RuntimeException("Curso não encontrado"));

        Campus campus = campusRepository.findById(dto.getIdCampus())
                .orElseThrow(() ->
                        new RuntimeException("Campus não encontrado"));

        Turma turma = Turma.builder()
                .anoLetivo(dto.getAnoLetivo())
                .serie(dto.getSerie())
                .curso(curso)
                .campus(campus)
                .build();

        return turmaRepository.save(turma);
    }

    public Turma updateParcial(Integer id, TurmaUpdateDto dto) {

        Turma turma = findById(id);

        if (dto.getAnoLetivo() != null) {
            turma.setAnoLetivo(dto.getAnoLetivo());
        }

        if (dto.getSerie() != null) {
            turma.setSerie(dto.getSerie());
        }

        if (dto.getIdCurso() != null) {

            Curso curso = cursoRepository.findById(dto.getIdCurso())
                    .orElseThrow(() ->
                            new RuntimeException("Curso não encontrado"));

            turma.setCurso(curso);
        }

        if (dto.getIdCampus() != null) {

            Campus campus = campusRepository.findById(dto.getIdCampus())
                    .orElseThrow(() ->
                            new RuntimeException("Campus não encontrado"));

            turma.setCampus(campus);
        }

        return turmaRepository.save(turma);
    }

    public void delete(Integer id) {

        Turma turma = findById(id);

        turmaRepository.delete(turma);
    }

    public List<Turma> findAllWithFilters(
            String serie,
            Integer anoLetivo,
            String curso,
            String campus) {

        if ((serie == null || serie.isBlank())
                && anoLetivo == null
                && (curso == null || curso.isBlank())
                && (campus == null || campus.isBlank())) {

            return turmaRepository.findAll();
        }

        return turmaRepository.findByFilters(
                serie,
                anoLetivo,
                curso,
                campus
        );
    }
}