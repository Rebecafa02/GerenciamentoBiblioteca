package com.biblioteca.gerenciamento.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "livro")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Disciplina é obrigatória")
    private String disciplina;

    @NotBlank(message = "Ano escolar é obrigatório")
    @Column(name = "ano_escolar")
    private String anoEscolar;

    @NotBlank(message = "Edição é obrigatória")
    private String edicao;
}