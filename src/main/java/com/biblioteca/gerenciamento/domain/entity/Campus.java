package com.biblioteca.gerenciamento.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Campus {
    @Id
    private int id;
    private String nome;
    private String cidade;

}

