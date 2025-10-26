package br.com.web.finalProject.service;

import br.com.web.finalProject.entities.FormaPagamento;
import br.com.web.finalProject.repositories.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    // CREATE
    public FormaPagamento criarFormaPagamento(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    // READ (Todos)
    public List<FormaPagamento> listarTodas() {
        return formaPagamentoRepository.findAll();
    }

    // READ (Por ID)
    public Optional<FormaPagamento> buscarPorId(Long id) {
        return formaPagamentoRepository.findById(id);
    }

    // UPDATE
    public Optional<FormaPagamento> atualizarFormaPagamento(Long id, FormaPagamento dadosForma) {
        return formaPagamentoRepository.findById(id).map(forma -> {
            forma.setTipo(dadosForma.getTipo());
            return formaPagamentoRepository.save(forma);
        });
    }

    // DELETE
    public boolean deletarFormaPagamento(Long id) {
        if (formaPagamentoRepository.existsById(id)) {
            // Nota: Adicionar try-catch se houver restrições de chave estrangeira
            formaPagamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
