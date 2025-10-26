package br.com.web.finalProject.repositories;

import br.com.web.finalProject.entities.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {}
