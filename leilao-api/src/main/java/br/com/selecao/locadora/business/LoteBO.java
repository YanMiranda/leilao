package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.lote.Lote;
import br.com.selecao.locadora.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoteBO {

    @Autowired
    private LoteRepository loteRepository;

    public void deletar(Integer id) {
        loteRepository.deleteById(id);
    }

    public List<Lote> buscarTodos(){
        return loteRepository.findAll();
    }

    public Lote salvar(Lote lote) {
        return loteRepository.save(lote);
    }

    public Lote buscarPorId(Integer id) {
        return loteRepository.findById(id).orElse(null);
    }

    public Lote atualizar(Lote lote) {
        return loteRepository.saveAndFlush(lote);
    }
}
