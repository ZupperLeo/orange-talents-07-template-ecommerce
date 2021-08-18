package br.com.zup.mercado.livre.mercadolivre.controller;

import br.com.zup.mercado.livre.mercadolivre.config.service.Mail;
import br.com.zup.mercado.livre.mercadolivre.dto.PerguntaForm;
import br.com.zup.mercado.livre.mercadolivre.model.Pergunta;
import br.com.zup.mercado.livre.mercadolivre.model.Produto;
import br.com.zup.mercado.livre.mercadolivre.model.Usuario;
import br.com.zup.mercado.livre.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    @PersistenceContext
    //1
    private EntityManager manager;
    @Autowired
    //1
    private UsuarioRepository repository;

    @PostMapping(value = "/{id}/perguntas")
    @Transactional
    public ResponseEntity<String> perguntar(@RequestBody @Valid PerguntaForm form, @PathVariable Long id) {
        //1
        Usuario usuario = repository.findByLogin("user@email.com").get();
        //1
        Produto produto = manager.find(Produto.class, id);
        //1
        Pergunta pergunta = form.toModel(usuario, produto);
        manager.persist(pergunta);
        //1
        Mail sender = new Mail();

        return ResponseEntity.ok().body(sender.send(pergunta));
    }
}
