package br.com.web.finalProject.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_despesa;

    private String descricao;
    private Double valor;
    private Date data;

    // Relacionamento: N Despesas pertencem a 1 Usuário
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false) // Define a coluna da chave estrangeira
    private Usuario usuario;

    // Relacionamento: N Despesas usam 1 Forma de Pagamento
    @ManyToOne
    @JoinColumn(name = "id_forma", nullable = false) // Define a coluna da chave estrangeira
    private FormaPagamento formaPagamento;

    // Relacionamento: N Despesas podem ter N Categorias
    // Esta é a tabela "mais-para-mais"
    @ManyToMany
    @JoinTable(
            name = "despesa_categoria", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "id_despesa"), // Chave desta entidade (Despesa)
            inverseJoinColumns = @JoinColumn(name = "id_categoria") // Chave da outra entidade (Categoria)
    )
    private Set<Categoria> categorias;
}
