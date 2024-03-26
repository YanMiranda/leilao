package br.com.selecao.locadora.entity.leilao;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Calendar;

public class LeilaoListener {

    @PrePersist
    public void prePersist(Leilao leilao) {
        leilao.setCreatedAt(Calendar.getInstance().getTime());
        leilao.setUpdatedAt(leilao.getCreatedAt());
    }

    @PreUpdate
    public void preUpdate(Leilao leilao) {
        leilao.setUpdatedAt(Calendar.getInstance().getTime());
    }
}
