package br.com.web.finalProject.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    private String nome;
    private String descricao;

    // Relacionamento: N Categorias podem estar em N Despesas
    // 'mappedBy' indica que a entidade 'Despesa' é a "dona" deste relacionamento
    @ManyToMany(mappedBy = "categorias")
    private Set<Despesa> despesas;
}
