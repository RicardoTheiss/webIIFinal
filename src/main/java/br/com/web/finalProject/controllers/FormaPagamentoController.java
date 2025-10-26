package br.com.web.finalProject.controllers;

import br.com.web.finalProject.dto.DeleteResponse;
import br.com.web.finalProject.entities.FormaPagamento;
import br.com.web.finalProject.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formas-pagamento") // URL base
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    // CREATE (POST /api/formas-pagamento)
    @PostMapping
    public ResponseEntity<FormaPagamento> criarFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento novaForma = formaPagamentoService.criarFormaPagamento(formaPagamento);
        return new ResponseEntity<>(novaForma, HttpStatus.CREATED);
    }

    // READ - Todos (GET /api/formas-pagamento)
    @GetMapping
    public List<FormaPagamento> listarTodasFormasPagamento() {
        return formaPagamentoService.listarTodas();
    }

    // READ - Por ID (GET /api/formas-pagamento/{id})
    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscarFormaPagamentoPorId(@PathVariable Long id) {
        return formaPagamentoService.buscarPorId(id)
                .map(forma -> new ResponseEntity<>(forma, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE (PUT /api/formas-pagamento/{id})
    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> atualizarFormaPagamento(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoService.atualizarFormaPagamento(id, formaPagamento)
                .map(forma -> new ResponseEntity<>(forma, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE (DELETE /api/formas-pagamento/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deletarFormaPagamento(@PathVariable Long id) {
        try {
            boolean deletado = formaPagamentoService.deletarFormaPagamento(id);
            if (deletado) {
                DeleteResponse resposta = new DeleteResponse("OK", "OK");
                return new ResponseEntity<>(resposta, HttpStatus.OK);
            } else {
                DeleteResponse resposta = new DeleteResponse("ERRO", "Forma de pagamento não encontrada");
                return new ResponseEntity<>(resposta, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Captura erros (ex: Forma de Pagamento está em uso por uma Despesa)
            DeleteResponse resposta = new DeleteResponse("ERRO", "Não foi possível deletar: " + e.getMessage());
            return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
        }
    }
}
