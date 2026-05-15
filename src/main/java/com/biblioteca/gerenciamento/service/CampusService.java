package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.domain.entity.Campus;
import com.biblioteca.gerenciamento.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CampusService {

    @Autowired
    private CampusRepository campusRepository;

    public Optional<Campus> findById(int id) {
        return campusRepository.findById(id);
    }

    public ResponseEntity<Campus> create(Campus campus) {
        return ResponseEntity.ok(campusRepository.save(campus));
    }

    public ResponseEntity<Campus> update(int id, Campus campus) {
        Optional<Campus> optional = campusRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Campus existing = optional.get();
        if (!Objects.equals(existing.getNome(), campus.getNome())) {
            existing.setNome(campus.getNome());
        }
        if (!Objects.equals(existing.getCidade(), campus.getCidade())) {
            existing.setCidade(campus.getCidade());
        }
        return ResponseEntity.ok(campusRepository.save(existing));
    }

    public ResponseEntity<Void> delete(Integer id) {
        if (!campusRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        campusRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Campus>> findAll() {
        return ResponseEntity.ok(campusRepository.findAll());
    }
}
