package br.com.selecao.locadora.entity.empresa;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empresa")
@EqualsAndHashCode(of = "id")
@EntityListeners({EmpresaListener.class})
@SequenceGenerator(name = "seq_empresa", sequenceName = "seq_empresa", allocationSize = 1)
public class Empresa {

    @Id
    @Column(name = "id_emp")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_empresa")
    private Integer id;

    @NotNull
    @Column(name = "raz_soc_emp", length = 64)
    private String razaoSocial;

    @NotNull
    @CNPJ(message = "CNPJ inválido")
    @Column(name = "cnpj_emp", length = 32, unique = true)
    private String cnpj;

    @Column(name = "log_emp", length = 64)
    private String logradouro;

    @Column(name = "mun_emp", length = 64)
    private String municipio;

    @Column(name = "num_emp", length = 10)
    private String numero;

    @Column(name = "com_emp", length = 64)
    private String complemento;

    @Column(name = "bai_emp", length = 64)
    private String bairro;

    @Column(name = "cep_emp", length = 16)
    private String cep;

    @Column(name = "tel_emp", length = 32)
    private String telefone;

    @NotNull
    @Column(name = "ema_emp", length = 254)
    private String email;

    @Column(name = "sit_emp", length = 254)
    private String site;

    @NotNull
    @Column(name = "usu_emp", length = 20, unique = true)
    private String usuario;

    @Column(name = "sen_emp", length = 128)
    private String senha;

    @NotNull
    @Column(name = "cre_at_emp")
    private Date createdAt;

    @NotNull
    @Column(name = "upd_at_emp")
    private Date updatedAt;

    public Empresa(String razaoSocial, String cnpj, String telefone, String email) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
    }
}
