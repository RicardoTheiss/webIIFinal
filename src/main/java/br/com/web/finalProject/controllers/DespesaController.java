package br.com.web.finalProject.controllers;

import br.com.web.finalProject.dto.DeleteResponse;
import br.com.web.finalProject.entities.Despesa;
import br.com.web.finalProject.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    // CREATE (POST /api/despesas)
    @PostMapping
    public ResponseEntity<Despesa> criarDespesa(@RequestBody Despesa despesa) {
        // Para criar uma despesa, o JSON deve conter os IDs das entidades relacionadas
        // Ex: "usuario": {"id_usuario": 1}, "formaPagamento": {"id_forma": 1},
        //     "categorias": [{"id_categoria": 1}]
        Despesa novaDespesa = despesaService.criarDespesa(despesa);
        return new ResponseEntity<>(novaDespesa, HttpStatus.CREATED);
    }

    // READ - Todos (GET /api/despesas)
    @GetMapping
    public List<Despesa> listarTodasDespesas() {
        return despesaService.listarTodas();
    }

    // READ - Por ID (GET /api/despesas/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Despesa> buscarDespesaPorId(@PathVariable Long id) {
        return despesaService.buscarPorId(id)
                .map(despesa -> new ResponseEntity<>(despesa, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE (PUT /api/despesas/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Despesa> atualizarDespesa(@PathVariable Long id, @RequestBody Despesa despesa) {
        return despesaService.atualizarDespesa(id, despesa)
                .map(despesaAtualizada -> new ResponseEntity<>(despesaAtualizada, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE (DELETE /api/despesas/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deletarDespesa(@PathVariable Long id) {
        try {
            boolean deletado = despesaService.deletarDespesa(id);
            if (deletado) {
                DeleteResponse resposta = new DeleteResponse("OK", "OK");
                return new ResponseEntity<>(resposta, HttpStatus.OK);
            } else {
                DeleteResponse resposta = new DeleteResponse("ERRO", "Despesa não encontrada");
                return new ResponseEntity<>(resposta, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Erros de deleção geralmente não ocorrem em Despesa,
            // pois ela não é chave estrangeira em outras tabelas.
            DeleteResponse resposta = new DeleteResponse("ERRO", "Não foi possível deletar: " + e.getMessage());
            return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
        }
    }
}
