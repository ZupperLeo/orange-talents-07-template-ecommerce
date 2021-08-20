package br.com.zup.mercado.livre.mercadolivre.controller;

import br.com.zup.mercado.livre.mercadolivre.dto.ProdutoDTO;
import br.com.zup.mercado.livre.mercadolivre.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/produtos")
public class DetalhesProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping(value = "/{id}")
    public ProdutoDTO detalhar(@PathVariable Long id) {
        Produto produto = manager.find(Produto.class, id);
        return new ProdutoDTO(produto);
    }
}
