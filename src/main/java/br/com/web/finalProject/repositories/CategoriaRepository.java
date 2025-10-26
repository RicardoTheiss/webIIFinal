package br.com.web.finalProject.repositories;

import br.com.web.finalProject.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
