package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.UnidadeBO;
import br.com.selecao.locadora.entity.unidade.Unidade;
import br.com.selecao.locadora.entity.unidade.UnidadeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "unidades")
public class UnidadeService {

    @Autowired
    private UnidadeBO unidadeBO;

    @PostMapping
    @Transactional
    public ResponseEntity<Unidade> salvar(@RequestBody @Valid UnidadeRequest unidade) {
        return new ResponseEntity<>(unidadeBO.salvar(unidade), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Unidade> deletar(@PathVariable Long id) {
        unidadeBO.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(unidadeBO.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Unidade>> buscarTodos() {
        return new ResponseEntity<>(unidadeBO.buscarTodos(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Unidade> atualizar(@RequestBody @Valid UnidadeRequest unidade) {
        return new ResponseEntity<>(unidadeBO.atualizar(unidade), HttpStatus.OK);
    }
}