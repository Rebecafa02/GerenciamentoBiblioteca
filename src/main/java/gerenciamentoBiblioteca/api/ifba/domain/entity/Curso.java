package gerenciamentoBiblioteca.api.ifba.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Curso{
    private int id;
    private String nome_curso;
}
