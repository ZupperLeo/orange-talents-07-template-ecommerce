package br.com.zup.mercado.livre.mercadolivre.controller;

import br.com.zup.mercado.livre.mercadolivre.dto.CategoriaForm;
import br.com.zup.mercado.livre.mercadolivre.model.Categoria;
import br.com.zup.mercado.livre.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Categoria> criaCategoria(@RequestBody @Valid CategoriaForm form) {
        Categoria categoria = form.toModel(manager);
        manager.persist(categoria);

        return ResponseEntity.ok().body(categoria);
    }
}
