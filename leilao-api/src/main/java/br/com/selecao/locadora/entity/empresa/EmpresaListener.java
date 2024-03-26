package br.com.selecao.locadora.entity.empresa;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Calendar;

public class EmpresaListener {

    @PrePersist
    public void prePersist(Empresa empresa) {
        empresa.setCreatedAt(Calendar.getInstance().getTime());
        empresa.setUpdatedAt(empresa.getCreatedAt());
    }

    @PreUpdate
    public void preUpdate(Empresa empresa) {
        empresa.setUpdatedAt(Calendar.getInstance().getTime());
    }
}
