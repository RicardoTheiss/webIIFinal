package br.com.web.finalProject.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_forma;

    private String tipo;

    // Relacionamento: 1 Forma de Pagamento pode estar em N Despesas
    @OneToMany(mappedBy = "formaPagamento")
    private Set<Despesa> despesas;
}
