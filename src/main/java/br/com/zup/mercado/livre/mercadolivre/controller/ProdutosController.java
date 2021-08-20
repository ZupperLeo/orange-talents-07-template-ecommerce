package br.com.zup.mercado.livre.mercadolivre.controller;

import br.com.zup.mercado.livre.mercadolivre.config.seguranca.UsuarioLogado;
import br.com.zup.mercado.livre.mercadolivre.dto.ImagemForm;
import br.com.zup.mercado.livre.mercadolivre.dto.OpiniaoForm;
import br.com.zup.mercado.livre.mercadolivre.dto.ProdutoForm;
import br.com.zup.mercado.livre.mercadolivre.model.Opiniao;
import br.com.zup.mercado.livre.mercadolivre.model.Produto;
import br.com.zup.mercado.livre.mercadolivre.model.Uploader;
import br.com.zup.mercado.livre.mercadolivre.model.Usuario;
import br.com.zup.mercado.livre.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private Uploader uploaderSimulator;

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid ProdutoForm form) {
        Usuario usuario = repository.findById(8L).get();
        Produto produto = form.toModel(manager, usuario);
        manager.persist(produto);

        return ResponseEntity.ok().body(produto);
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<Produto> adicionaImagem(@PathVariable Long id, @Valid ImagemForm form) {
        Usuario usuario = repository.findById(9L).get();
        Produto produto = manager.find(Produto.class, id);

        if(!produto.naoPerteenceAoUsuario(usuario)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderSimulator.envia(form.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);

        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/{id}/opiniao")
    @Transactional
    public ResponseEntity<Opiniao> opinar(@RequestBody @Valid OpiniaoForm form, @PathVariable Long id,
                                @Valid UsuarioLogado usuarioLogado){
        Produto produto = manager.find(Produto.class, id);
        Usuario usuario = usuarioLogado.get();
        Opiniao opiniao = form.toModel(produto, usuario);
        manager.persist(opiniao);

        return ResponseEntity.ok().build();
    }

}
