package br.com.selecao.locadora.entity.unidade;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unidade")
@Entity(name = "Unidade")
@EqualsAndHashCode(of = "id")
@EntityListeners({UnidadeListener.class})
@SequenceGenerator(name = "seq_unidade", sequenceName = "seq_unidade", allocationSize = 1)
public class Unidade implements Serializable {

    @Id
    @Column(name = "id_uni")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_unidade")
    private Long id;

    @NotNull
    @Column(name = "nom_uni", length = 128)
    private String nome;

    @NotNull
    @Column(name = "cre_at_uni")
    private Date createdAt;

    @NotNull
    @Column(name = "upd_at_uni")
    private Date updatedAt;
}

