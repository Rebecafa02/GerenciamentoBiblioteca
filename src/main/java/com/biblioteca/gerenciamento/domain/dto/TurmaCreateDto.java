package com.biblioteca.gerenciamento.domain.dto;

import lombok.Data;

@Data
public class TurmaCreateDto {

    private Integer anoLetivo;

    private String serie;

    private Integer idCurso;

    private Integer idCampus;
}