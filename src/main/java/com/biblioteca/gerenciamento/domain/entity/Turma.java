package com.biblioteca.gerenciamento.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Turma {
    @Id
    private int id;
    @NotNull(message = "Ano letivo é obrigatório")
    @Min(value = 2000, message = "Ano letivo inválido")
    private Integer anoLetivo;

    @NotBlank(message = "Série é obrigatória")
    private String serie;

    @NotNull(message = "Curso é obrigatório")
    private Integer idCurso;

    @NotNull(message = "Campus é obrigatório")
    private Integer idCampus;
}

