package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.unidade.Unidade;
import br.com.selecao.locadora.entity.unidade.UnidadeRequest;
import br.com.selecao.locadora.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeBO {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<Unidade> buscarTodos(){
        return unidadeRepository.findAll();
    }

    public Unidade salvar(UnidadeRequest unidade) {
        Unidade item = new Unidade();
        item.setNome(unidade.getNome());
        return unidadeRepository.save(item);
    }

    public void deletar(Long id) {
        unidadeRepository.deleteById(id);
    }

    public Unidade buscarPorId(Long id) {
        return unidadeRepository.findById(id).orElse(null);
    }

    public Unidade atualizar(UnidadeRequest unidade) {
        Unidade item = unidadeRepository.findById(unidade.getId()).orElse(null);
        if (item == null) {
            return null;
        }
        item.setNome(unidade.getNome());
        return unidadeRepository.saveAndFlush(item);
    }
}
