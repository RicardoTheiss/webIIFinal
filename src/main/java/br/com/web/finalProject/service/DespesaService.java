package br.com.web.finalProject.service;

import br.com.web.finalProject.entities.Despesa;
import br.com.web.finalProject.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa criarDespesa(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    // READ (Todos)
    public List<Despesa> listarTodas() {
        return despesaRepository.findAll();
    }

    // READ (Por ID)
    public Optional<Despesa> buscarPorId(Long id) {
        return despesaRepository.findById(id);
    }

    // UPDATE
    public Optional<Despesa> atualizarDespesa(Long id, Despesa dadosDespesa) {
        return despesaRepository.findById(id).map(despesa -> {
            despesa.setDescricao(dadosDespesa.getDescricao());
            despesa.setValor(dadosDespesa.getValor());
            despesa.setData(dadosDespesa.getData());

            // Atualiza os relacionamentos
            despesa.setUsuario(dadosDespesa.getUsuario());
            despesa.setFormaPagamento(dadosDespesa.getFormaPagamento());
            despesa.setCategorias(dadosDespesa.getCategorias());

            return despesaRepository.save(despesa);
        });
    }

    // DELETE
    public boolean deletarDespesa(Long id) {
        if (despesaRepository.existsById(id)) {
            despesaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
