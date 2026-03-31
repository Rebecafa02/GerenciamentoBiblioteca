package gerenciamentoBiblioteca.api.ifba.repository;

import gerenciamentoBiblioteca.api.ifba.domain.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusRepository extends JpaRepository<Campus, Integer> {
}
