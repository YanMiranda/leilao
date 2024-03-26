package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LoteBO;
import br.com.selecao.locadora.entity.lote.Lote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "lotes")
public class LoteService {

    @Autowired
    private LoteBO loteBO;

    @PostMapping
    @Transactional
    public ResponseEntity<Lote> salvar(@RequestBody @Valid Lote lote) {
        return new ResponseEntity<>(loteBO.salvar(lote), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/deletar")
    public ResponseEntity<Lote> deletar(@RequestBody Integer id) {
        loteBO.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lote> buscarPorId(@RequestBody Integer id) {
        return new ResponseEntity<>(loteBO.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Lote>> buscarTodos() {
        return new ResponseEntity<>(loteBO.buscarTodos(), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Lote> atualizar(@RequestBody @Valid Lote lote) {
        return new ResponseEntity<>(loteBO.atualizar(lote), HttpStatus.OK);
    }
}
