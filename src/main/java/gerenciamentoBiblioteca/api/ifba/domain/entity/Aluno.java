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
public class Aluno {
    @Id
    private int matricula;
    private String nome;
    private String foto_perfil;
    private String email;
    private String id_turma;

}
