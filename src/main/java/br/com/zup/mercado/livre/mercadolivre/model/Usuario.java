package br.com.zup.mercado.livre.mercadolivre.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {

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

    public Long getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
