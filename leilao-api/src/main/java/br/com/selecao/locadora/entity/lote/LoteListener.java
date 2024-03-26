package br.com.selecao.locadora.entity.lote;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Calendar;

public class LoteListener {

    @PrePersist
    public void prePersist(Lote lote) {
        lote.setCreatedAt(Calendar.getInstance().getTime());
        lote.setUpdatedAt(lote.getCreatedAt());
    }

    @PreUpdate
    public void preUpdate(Lote lote) {
        lote.setUpdatedAt(Calendar.getInstance().getTime());
    }
}
