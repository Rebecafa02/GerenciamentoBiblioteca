package com.biblioteca.gerenciamento.domain.dto;

import lombok.Data;
import java.util.List;

@Data
public class TurmaCreateDto {

    private Integer anoLetivo;

    private String serie;

    private Integer idCurso;

    private Integer idCampus;

    private List<Integer> materiasIds;
}