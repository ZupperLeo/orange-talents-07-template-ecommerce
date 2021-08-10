package br.com.zup.mercado.livre.mercadolivre.config.validatorhandler;

import br.com.zup.mercado.livre.mercadolivre.dto.UsuarioForm;
import br.com.zup.mercado.livre.mercadolivre.model.Usuario;
import br.com.zup.mercado.livre.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeEmailDuplicadoValidator implements Validator {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UsuarioForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        UsuarioForm usuarioForm = (UsuarioForm) target;
        Optional<Usuario> autor =  repository.findByLogin(usuarioForm.getLogin());

        if(autor.isPresent()) {
            errors.rejectValue("email", null,
    "O e-mail já está sendo utilizado por outra pessoa " + usuarioForm.getLogin());
        }
    }
}
