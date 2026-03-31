package gerenciamentoBiblioteca.api.ifba.service;

import gerenciamentoBiblioteca.api.ifba.domain.entity.Campus;
import gerenciamentoBiblioteca.api.ifba.repository.CampusRepository;
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
        return ResponseEntity.ok(campusRepository.findById(id)).getBody();
    }

    public ResponseEntity<Campus> create(Campus campus) {
        return ResponseEntity.ok(campusRepository.save(campus));
    }

    public ResponseEntity<Campus> update(int id, Campus campus) {
        Campus oldCampus = campusRepository.findById(id).get();
        if (!Objects.equals(oldCampus.getNome(), campus.getNome())) {
            oldCampus.setNome(campus.getNome());
        }
        if (!Objects.equals(oldCampus.getCidade(), campus.getCidade())){
            oldCampus.setCidade(campus.getCidade());
        }

        return ResponseEntity.ok(campusRepository.save(oldCampus));
    }

    public ResponseEntity<Void> delete(Integer id) {
        campusRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Campus>> findAll() {
        return ResponseEntity.ok(campusRepository.findAll());
    }
}
