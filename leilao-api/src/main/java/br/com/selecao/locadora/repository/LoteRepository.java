package br.com.selecao.locadora.repository;

import br.com.selecao.locadora.entity.lote.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {
}
