package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.EmpresaBO;
import br.com.selecao.locadora.entity.empresa.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.util.List;

@RestController
@RequestMapping(value = "empresas")
public class EmpresaService {

    @Autowired
    private EmpresaBO empresaBO;

    @PostMapping
    @Transactional
    public ResponseEntity<Empresa> salvar(@RequestBody @Valid Empresa empresa) {
        return new ResponseEntity<>(empresaBO.salvar(empresa), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> deletar(@PathVariable Integer id) {
        empresaBO.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(empresaBO.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> buscarTodos() {
        return new ResponseEntity<>(empresaBO.buscarTodos(), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizar(@RequestBody @Valid Empresa empresa) {
        return new ResponseEntity<>(empresaBO.atualizar(empresa), HttpStatus.OK);
    }

    @GetMapping("/filtro")
    public ResponseEntity<Page<Empresa>> buscarPorFiltro(
             Pageable pagination,
             @RequestParam(value = "razaoSocial", required = false) String razaoSocial,
             @RequestParam(value = "cnpj", required = false) String cnpj,
             @RequestParam(value = "telefone", required = false) String telefone,
             @RequestParam(value = "email", required = false) String email
    ) {
        Page<Empresa> empresas = empresaBO.buscarPorFiltro(razaoSocial, cnpj, telefone, email, pagination);
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }
}
