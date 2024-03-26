package br.com.selecao.locadora.entity;

import br.com.selecao.locadora.entity.empresa.Empresa;
import br.com.selecao.locadora.entity.leilao.Leilao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comprador")
@Entity(name = "Comprador")
public class Comprador implements Serializable {

    @Id
    private Integer empresa;

    @Id
    private Integer leilao;

    @ManyToOne
    @JoinColumn(name = "id_emp", insertable = false, updatable = false)
    private Empresa empresaObj;

    @ManyToOne
    @JoinColumn(name = "id_lei", insertable = false, updatable = false)
    private Leilao leilaoObj;

}

