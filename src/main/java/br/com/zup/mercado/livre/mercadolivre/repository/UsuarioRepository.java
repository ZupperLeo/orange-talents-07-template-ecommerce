package br.com.zup.mercado.livre.mercadolivre.repository;

import br.com.zup.mercado.livre.mercadolivre.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);
}
