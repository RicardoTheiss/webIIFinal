package br.com.web.finalProject.controllers;

import br.com.web.finalProject.dto.DeleteResponse;
import br.com.web.finalProject.entities.Usuario;
import br.com.web.finalProject.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // CREATE (POST /api/usuarios)
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    // READ - Todos (GET /api/usuarios)
    @GetMapping
    public List<Usuario> listarTodosUsuarios() {
        return usuarioService.listarTodos();
    }

    // READ - Por ID (GET /api/usuarios/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE (PUT /api/usuarios/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario)
                .map(usuarioAtualizado -> new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE (DELETE /api/usuarios/{id})
    // Implementando a resposta JSON customizada
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deletarUsuario(@PathVariable Long id) {
        try {
            boolean deletado = usuarioService.deletarUsuario(id);
            if (deletado) {
                // Sucesso [cite: 17, 20]
                DeleteResponse resposta = new DeleteResponse("OK", "OK");
                return new ResponseEntity<>(resposta, HttpStatus.OK);
            } else {
                // Não encontrou o recurso [cite: 18, 20]
                DeleteResponse resposta = new DeleteResponse("ERRO", "Usuário não encontrado");
                return new ResponseEntity<>(resposta, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Outro erro (ex: violação de chave estrangeira) [cite: 18, 20]
            DeleteResponse resposta = new DeleteResponse("ERRO", "Erro ao deletar: " + e.getMessage());
            return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
        }
    }
}
