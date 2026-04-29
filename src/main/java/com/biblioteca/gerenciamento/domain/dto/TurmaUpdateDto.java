package com.biblioteca.gerenciamento.domain.dto;

import lombok.Data;

@Data
public class TurmaUpdateDto {
    private Integer id;
    private Integer anoLetivo;
    private String serie;
    private Integer idCurso;
    private Integer idCampus;
}
