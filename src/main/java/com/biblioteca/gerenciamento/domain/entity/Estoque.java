package com.biblioteca.gerenciamento.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estoque")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @Column(name = "qtd_total")
    private Integer qtdTotal;

    @Column(name = "qtd_disponivel")
    private Integer qtdDisponivel;

    @Column(name = "qtd_novo")
    private Integer qtdNovo;

    @Column(name = "qtd_conservado")
    private Integer qtdConservado;

    @Column(name = "qtd_mal_conservado")
    private Integer qtdMalConservado;

    @Column(name = "qtd_inutilizado")
    private Integer qtdInutilizado;
}