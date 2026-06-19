package com.biblioteca.gerenciamento.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome da matéria é obrigatório")
    @Size(min = 2, max = 150, message = "O nome da matéria deve ter entre 2 e 150 caracteres")
    @Column(name = "nome_materia", nullable = false)
    private String nomeMateria;
}
