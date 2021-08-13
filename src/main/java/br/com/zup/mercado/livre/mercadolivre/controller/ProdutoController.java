package br.com.zup.mercado.livre.mercadolivre.controller;

import br.com.zup.mercado.livre.mercadolivre.dto.ProdutoForm;
import br.com.zup.mercado.livre.mercadolivre.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid ProdutoForm form) {
        Produto produto = form.toModel(manager);

        return ResponseEntity.ok().body(produto);
    }
}
