package gerenciamentoBiblioteca.api.ifba.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Turma {
    @Id
    private int id;
    private int ano_letivo;
    private String serie;
    private int id_curso;
    private int id_campus;
}

