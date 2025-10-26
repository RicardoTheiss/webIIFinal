package br.com.web.finalProject.controllers;

import br.com.web.finalProject.dto.DeleteResponse;
import br.com.web.finalProject.entities.Categoria;
import br.com.web.finalProject.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // CREATE (POST /api/categorias)
    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.criarCategoria(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    // READ - Todos (GET /api/categorias)
    @GetMapping
    public List<Categoria> listarTodasCategorias() {
        return categoriaService.listarTodas();
    }

    // READ - Por ID (GET /api/categorias/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE (PUT /api/categorias/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return categoriaService.atualizarCategoria(id, categoria)
                .map(cat -> new ResponseEntity<>(cat, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE (DELETE /api/categorias/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deletarCategoria(@PathVariable Long id) {
        try {
            boolean deletado = categoriaService.deletarCategoria(id);
            if (deletado) {
                DeleteResponse resposta = new DeleteResponse("OK", "OK");
                return new ResponseEntity<>(resposta, HttpStatus.OK);
            } else {
                DeleteResponse resposta = new DeleteResponse("ERRO", "Categoria não encontrada");
                return new ResponseEntity<>(resposta, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Captura erros (ex: Categoria está em uso por uma Despesa)
            DeleteResponse resposta = new DeleteResponse("ERRO", "Não foi possível deletar: " + e.getMessage());
            return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
        }
    }
}
