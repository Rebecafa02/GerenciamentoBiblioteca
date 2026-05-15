package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.config.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.domain.entity.Campus;
import com.biblioteca.gerenciamento.repository.CampusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository campusRepository;

    public Campus findById(Integer id) {
        return campusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campus não encontrado"));
    }

    public Campus create(Campus campus) {
        return campusRepository.save(campus);
    }

    public Campus update(Integer id, Campus campus) {

        Campus oldCampus = findById(id);

        oldCampus.setNome(campus.getNome());
        oldCampus.setCidade(campus.getCidade());

        return campusRepository.save(oldCampus);
    }

    public void delete(Integer id) {

        Campus campus = findById(id);

        campusRepository.delete(campus);
    }

    public List<Campus> findAll(String nome, String cidade) {

        boolean hasNome = nome != null && !nome.isBlank();
        boolean hasCidade = cidade != null && !cidade.isBlank();

        if (hasNome && hasCidade) {
            return campusRepository
                    .findByNomeContainingIgnoreCaseAndCidadeContainingIgnoreCase(
                            nome,
                            cidade
                    );
        }

        if (hasNome) {
            return campusRepository
                    .findByNomeContainingIgnoreCase(nome);
        }

        if (hasCidade) {
            return campusRepository
                    .findByCidadeContainingIgnoreCase(cidade);
        }

        return campusRepository.findAll();
    }
}