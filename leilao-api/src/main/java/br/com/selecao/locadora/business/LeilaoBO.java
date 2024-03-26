package br.com.selecao.locadora.business;

import br.com.selecao.locadora.entity.leilao.Leilao;
import br.com.selecao.locadora.repository.LeilaoRepository;
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
import java.util.Date;
import java.util.List;

@Service
public class LeilaoBO {

    @Autowired
    private LeilaoRepository leilaoRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Leilao> buscarTodos(){
        return leilaoRepository.findAll();
    }

    public Leilao salvar(Leilao leilao) {
        return leilaoRepository.save(leilao);
    }

    public void deletar(Integer id) {
        leilaoRepository.deleteById(id);
    }

    public Leilao buscarPorId(Integer id) {
        return leilaoRepository.findById(id).orElse(null);
    }

    public Leilao atualizar(Leilao leilao) {
        return leilaoRepository.saveAndFlush(leilao);
    }

    public Page<Leilao> buscarPorFiltro(Pageable pagination, Integer codigo, String descricao, Integer vendedor, Date inicioPrevisto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Leilao> query = cb.createQuery(Leilao.class);
        Root<Leilao> root = query.from(Leilao.class);

        Predicate predicate = buildPredicate(cb, root, codigo, descricao, vendedor, inicioPrevisto);

        query.where(predicate);

        TypedQuery<Leilao> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pagination.getPageNumber() * pagination.getPageSize());
        typedQuery.setMaxResults(pagination.getPageSize());

        List<Leilao> results = typedQuery.getResultList();

        long count = countFilteredEntities(codigo, descricao, vendedor, inicioPrevisto);

        return new PageImpl<>(results, pagination, count);
    }

    private long countFilteredEntities(Integer codigo, String descricao, Integer vendedor, Date inicioPrevisto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Leilao> root = query.from(Leilao.class);

        Predicate predicate = buildPredicate(cb, root, codigo, descricao, vendedor, inicioPrevisto);

        query.select(cb.count(root)).where(predicate);

        return entityManager.createQuery(query).getSingleResult();
    }

    private Predicate buildPredicate(CriteriaBuilder cb, Root<Leilao> root, Integer codigo, String descricao, Integer vendedor, Date inicioPrevisto) {
        Predicate predicate = cb.conjunction();

        if (codigo != null) {
            predicate = cb.and(predicate, cb.equal(root.get("codigo"), codigo));
        }
        if (descricao != null && !descricao.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("descricao"), descricao));
        }
        if (vendedor != null) {
            predicate = cb.and(predicate, cb.equal(root.get("vendedor"), vendedor));
        }
        if (inicioPrevisto != null) {
            predicate = cb.and(predicate, cb.equal(root.get("inicioPrevisto"), inicioPrevisto));
        }

        return predicate;
    }
}
