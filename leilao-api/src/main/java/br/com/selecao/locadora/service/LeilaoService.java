package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LeilaoBO;
import br.com.selecao.locadora.entity.leilao.Leilao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "leiloes")
public class LeilaoService {

    @Autowired
    private LeilaoBO leilaoBO;

    @PostMapping
    @Transactional
    public ResponseEntity<Leilao> salvar(@RequestBody @Valid Leilao leilao) {
        return new ResponseEntity<>(leilaoBO.salvar(leilao), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Leilao> deletar(@PathVariable Integer id) {
        leilaoBO.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leilao> buscarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(leilaoBO.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Leilao>> buscarTodos() {
        return new ResponseEntity<>(leilaoBO.buscarTodos(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Leilao> atualizar(@RequestBody @Valid Leilao leilao) {
        return new ResponseEntity<>(leilaoBO.atualizar(leilao), HttpStatus.OK);
    }

    @GetMapping("/filtro")
    public ResponseEntity<Page<Leilao>> buscarPorFiltro(
            Pageable pagination,
            @RequestParam(required = false) Integer codigo,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) Integer vendedor,
            @RequestParam(required = false) Date inicioPrevisto
    ) {
        return new ResponseEntity<>(leilaoBO.buscarPorFiltro(pagination, codigo, descricao, vendedor, inicioPrevisto), HttpStatus.OK);
    }

}
