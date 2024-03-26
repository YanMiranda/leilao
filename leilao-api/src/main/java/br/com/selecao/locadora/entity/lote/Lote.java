package br.com.selecao.locadora.entity.lote;

import br.com.selecao.locadora.entity.leilao.Leilao;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lote")
@Entity(name = "Lote")
@EqualsAndHashCode(of = "id")
@EntityListeners({LoteListener.class})
public class Lote {

    @Id
    @Column(name = "id_lot")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "num_lot")
    private Integer numeroLote;

    @NotNull
    @Column(name = "des_lot", length = 60)
    private String descricao;

    @NotNull
    @Column(name = "qtd_lot")
    private BigDecimal quantidade;

    @Column(name = "val_ini_lot")
    private BigDecimal valorInicial;

    @NotNull
    @Column(name = "val_ult_lot", length = 128)
    private String unidade;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_lei")
    private Leilao leilao;

    @NotNull
    @Column(name = "cre_at_lot")
    private Date createdAt;

    @NotNull
    @Column(name = "upd_at_lot")
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
