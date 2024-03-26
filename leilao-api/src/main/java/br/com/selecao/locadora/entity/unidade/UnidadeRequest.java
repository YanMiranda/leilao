package br.com.selecao.locadora.entity.unidade;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UnidadeRequest {
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

}
