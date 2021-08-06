package br.com.zup.mercado.livre.mercadolivre.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;
    private @NotBlank @Email String login;
    private @NotBlank String senha;
    private @NotNull LocalDateTime dataCadastro = LocalDateTime.now();

    public Usuario() {
    }

    /**
    * @param login string no formato email
    * @param senhaLimpa string em texto limpo
    */
    public Usuario(@NotBlank @Email String login, @NotNull SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(login), "Login não pode estar em branco");
        Assert.notNull(senhaLimpa, "O objeto do tipo SenhaLimpa não pode ser nulo");

        this.login = login;
        this.senha = senhaLimpa.hash();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
