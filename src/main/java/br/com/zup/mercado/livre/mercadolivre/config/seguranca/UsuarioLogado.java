package br.com.zup.mercado.livre.mercadolivre.config.seguranca;

import br.com.zup.mercado.livre.mercadolivre.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public class UsuarioLogado implements UserDetails {

    private final Usuario usuario;
    private final User springUserDetails;

    public UsuarioLogado(@NotNull @Valid Usuario usuario) {
        this.usuario = usuario;
        this.springUserDetails = new User(usuario.getLogin(), usuario.getSenha(), List.of());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return springUserDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return springUserDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return springUserDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return springUserDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return springUserDetails.isAccountNonExpired();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return springUserDetails.isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return springUserDetails.isEnabled();
    }

    public Usuario get() {
        return usuario;
    }
}
