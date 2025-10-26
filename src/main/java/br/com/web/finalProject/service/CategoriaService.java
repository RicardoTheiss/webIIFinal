package br.com.web.finalProject.service;

import br.com.web.finalProject.entities.Categoria;
import br.com.web.finalProject.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // CREATE
    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // READ (Todos)
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    // READ (Por ID)
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    // UPDATE
    public Optional<Categoria> atualizarCategoria(Long id, Categoria dadosCategoria) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNome(dadosCategoria.getNome());
            categoria.setDescricao(dadosCategoria.getDescricao());
            return categoriaRepository.save(categoria);
        });
    }

    // DELETE
    public boolean deletarCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            // Nota: Adicionar try-catch se houver restrições de chave estrangeira
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
