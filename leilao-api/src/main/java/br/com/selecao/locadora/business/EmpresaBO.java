package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.empresa.Empresa;
import br.com.selecao.locadora.repository.IEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class EmpresaBO {

    @Autowired
    private IEmpresaRepository empresaRepository;

    @Autowired
    private EntityManager entityManager;

    public void deletar(Integer id) {
        empresaRepository.deleteById(id);
    }

    public Empresa buscarPorId(Integer id) {
        return empresaRepository.findById(id).orElse(null);
    }

    public List<Empresa> buscarTodos() {
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas;
    }

    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa atualizar(Empresa empresa) {
        return empresaRepository.saveAndFlush(empresa);
    }

    public Page<Empresa> buscarPorFiltro(String razaoSocial, String cnpj, String telefone, String email, Pageable pagination) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Empresa> query = cb.createQuery(Empresa.class);
        Root<Empresa> root = query.from(Empresa.class);

        Predicate predicate = cb.conjunction();

        if (razaoSocial != null && !razaoSocial.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("razaoSocial"), razaoSocial));
        }
        if (cnpj != null && !cnpj.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("cnpj"), cnpj));
        }
        if (telefone != null && !telefone.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("telefone"), telefone));
        }
        if (email != null && !email.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("email"), email));
        }

        query.where(predicate);

        TypedQuery<Empresa> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pagination.getOffset());
        typedQuery.setMaxResults(pagination.getPageSize());

        List<Empresa> results = typedQuery.getResultList();

        long count = countFilteredEntities(razaoSocial, cnpj, telefone, email);

        return new PageImpl<>(results, pagination, count);
    }

    private long countFilteredEntities(String razaoSocial, String cnpj, String telefone, String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Empresa> root = query.from(Empresa.class);

        Predicate predicate = cb.conjunction();

        if (razaoSocial != null && !razaoSocial.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("razaoSocial"), razaoSocial));
        }
        if (cnpj != null && !cnpj.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("cnpj"), cnpj));
        }
        if (telefone != null && !telefone.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("telefone"), telefone));
        }
        if (email != null && !email.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("email"), email));
        }

        query.select(cb.count(root)).where(predicate);

        return entityManager.createQuery(query).getSingleResult();
    }
}
