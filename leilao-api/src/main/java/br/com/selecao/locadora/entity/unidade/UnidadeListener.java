package br.com.selecao.locadora.entity.unidade;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Calendar;

public class UnidadeListener {

    @PrePersist
    public void prePersist(Unidade unidade) {
        unidade.setCreatedAt(Calendar.getInstance().getTime());
        unidade.setUpdatedAt(unidade.getCreatedAt());
    }

    @PreUpdate
    public void preUpdate(Unidade unidade) {
        unidade.setUpdatedAt(Calendar.getInstance().getTime());
    }
}
