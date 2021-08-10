package br.com.zup.mercado.livre.mercadolivre.config;

import br.com.zup.mercado.livre.mercadolivre.config.seguranca.UsuarioLogado;
import br.com.zup.mercado.livre.mercadolivre.config.seguranca.UserDetailsMapper;
import br.com.zup.mercado.livre.mercadolivre.model.Usuario;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AppUserDetailMapper implements UserDetailsMapper {
    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario)shouldBeASystemUser);
    }
}
