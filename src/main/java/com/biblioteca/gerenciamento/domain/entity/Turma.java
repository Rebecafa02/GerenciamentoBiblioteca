package com.biblioteca.gerenciamento.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Ano letivo é obrigatório")
    @Min(value = 2000, message = "Ano letivo inválido")
    @Max(value = 2100, message = "Ano letivo inválido")
    private Integer anoLetivo;

    @NotBlank(message = "Série é obrigatória")
    @Size(min = 1, max = 20, message = "Série inválida")
    private String serie;

    @NotNull(message = "Curso é obrigatório")
    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @NotNull(message = "Campus é obrigatório")
    @ManyToOne
    @JoinColumn(name = "id_campus", nullable = false)
    private Campus campus;
}