package gerenciamentoBiblioteca.api.ifba.controller;

import gerenciamentoBiblioteca.api.ifba.domain.entity.Campus;
import gerenciamentoBiblioteca.api.ifba.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/campus")
public class CampusController {

    @Autowired
    private CampusService campusService;

    @PostMapping
    ResponseEntity<Campus> createCampus(@RequestBody Campus campus) {
        return campusService.create(campus);
    }

    @PutMapping("/{id")
    ResponseEntity<Campus> updateCampus(@PathVariable int id, @RequestBody Campus campus) {
        return campusService.update(id, campus);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCampus(@PathVariable int id) {
        return campusService.delete(id);
    }

    @GetMapping("/{id}")
    Optional<Campus> findCampusById(@PathVariable int id) {
        return campusService.findById(id);
    }

    @GetMapping
    ResponseEntity<List<Campus>> findAllCampus() {
        return campusService.findAll();
    }
}
