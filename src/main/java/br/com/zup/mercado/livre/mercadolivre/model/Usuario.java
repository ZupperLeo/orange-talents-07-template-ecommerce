package br.com.zup.mercado.livre.mercadolivre.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;
    private @NotBlank @Email String login;
    private @NotBlank @Length(min = 6) String senha;
    private @NotNull @PastOrPresent LocalDateTime dataCadastro = LocalDateTime.now();

    @Deprecated
    public Usuario() {
    }

    /**
    * @param login string no formato email
    * @param senhaLimpa string em texto limpo
    */
    public Usuario(@NotBlank @Email String login, @NotNull @Valid SenhaLimpa senhaLimpa) {
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

    public Long getId() {
        return this.id;
    }

    public String getSenha() {
        return senha;
    }

    public String getLogin() {
        return this.login;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        return true;
    }

}
