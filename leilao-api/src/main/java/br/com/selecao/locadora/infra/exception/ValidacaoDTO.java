package br.com.selecao.locadora.infra.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter
@Setter
public class ValidacaoDTO {
    private String campo;
    private String mensagem;

    public ValidacaoDTO(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    ValidacaoDTO(FieldError fieldError) {
        this.campo = fieldError.getField();
        this.mensagem = fieldError.getDefaultMessage();
    }

}
