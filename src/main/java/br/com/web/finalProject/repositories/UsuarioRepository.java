package br.com.web.finalProject.repositories;

import br.com.web.finalProject.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
