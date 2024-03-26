package br.com.selecao.locadora.entity.leilao;

import com.sun.istack.NotNull;
import jdk.jfr.Timestamp;
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
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "leilao")
@Entity(name = "Leilao")
@EqualsAndHashCode(of = "id")
@EntityListeners({LeilaoListener.class})
@SequenceGenerator(name = "seq_leilao", sequenceName = "seq_leilao", allocationSize = 1)
public class Leilao {

    @Id
    @Column(name = "id_lei")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_leilao")
    private Integer id;

    @Column(name = "cod_lei")
    private Integer codigo;

    @NotNull
    @Column(name = "des_lei", length = 128)
    private String descricao;

    @NotNull
    @Column(name = "ven_lei")
    private Integer vendedor;

    @NotNull
    @Timestamp
    @Column(name = "ini_pre_lei")
    private Date inicioPrevisto;

    @NotNull
    @Column(name = "cre_at_lei")
    private Date createdAt;

    @NotNull
    @Column(name = "upd_at_lei")
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}


