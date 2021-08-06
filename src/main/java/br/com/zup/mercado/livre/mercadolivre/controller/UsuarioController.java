package br.com.zup.mercado.livre.mercadolivre.controller;

import br.com.zup.mercado.livre.mercadolivre.dto.UsuarioForm;
import br.com.zup.mercado.livre.mercadolivre.model.Usuario;
import br.com.zup.mercado.livre.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> cadastra(@RequestBody @Valid UsuarioForm form) {
        Usuario usuario = form.converte();
        repository.save(usuario);

        return ResponseEntity.ok().body(usuario);
    }
}
