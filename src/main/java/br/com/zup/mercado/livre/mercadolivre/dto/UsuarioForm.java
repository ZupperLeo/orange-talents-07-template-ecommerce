package br.com.zup.mercado.livre.mercadolivre.dto;

import br.com.zup.mercado.livre.mercadolivre.model.SenhaLimpa;
import br.com.zup.mercado.livre.mercadolivre.model.Usuario;
import br.com.zup.mercado.livre.mercadolivre.config.validator.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioForm {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;



    public UsuarioForm(@NotBlank @Email String login, @NotBlank String senha) {
        this.login = login;
        this.senha = senha;
    }


    public Usuario converte() {
       return new Usuario(this.login, new SenhaLimpa(senha));
    }

    public String getLogin() {
        return login;
    }
}
