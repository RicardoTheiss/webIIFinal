package br.com.web.finalProject.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nome;
    private String email;
    private String senha;

    // Relacionamento: 1 Usuário pode ter N Despesas
    @OneToMany(mappedBy = "usuario")
    private Set<Despesa> despesas;
}
