package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.config.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.domain.entity.Materia;
import com.biblioteca.gerenciamento.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaService {

    private final MateriaRepository materiaRepository;

    public Materia create(Materia materia) {
        return materiaRepository.save(materia);
    }

    public List<Materia> findAll(String nome) {
        if (nome != null && !nome.isBlank()) {
            return materiaRepository.findByNomeMateriaContainingIgnoreCase(nome);
        }
        return materiaRepository.findAll();
    }

    public Materia findById(Integer id) {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matéria não encontrada com id: " + id));
    }

    public Materia update(Integer id, Materia materiaAtualizada) {
        Materia materia = findById(id);
        materia.setNomeMateria(materiaAtualizada.getNomeMateria());
        return materiaRepository.save(materia);
    }

    public void delete(Integer id) {
        Materia materia = findById(id);
        materiaRepository.delete(materia);
    }
}
