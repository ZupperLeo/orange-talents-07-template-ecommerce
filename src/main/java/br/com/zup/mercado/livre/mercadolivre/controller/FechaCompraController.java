package br.com.zup.mercado.livre.mercadolivre.controller;

import br.com.zup.mercado.livre.mercadolivre.dto.CompraForm;
import br.com.zup.mercado.livre.mercadolivre.model.Compra;
import br.com.zup.mercado.livre.mercadolivre.model.GatewayPagamento;
import br.com.zup.mercado.livre.mercadolivre.model.Produto;
import br.com.zup.mercado.livre.mercadolivre.model.Usuario;
import br.com.zup.mercado.livre.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class FechaCompraController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> comprar(@RequestBody @Valid CompraForm form) throws BindException {
        Produto produtoASerComprado = manager.find(Produto.class, form.getProdutoId());
        boolean isOk = produtoASerComprado.verificaEstoque(form.getQtde());

        if(isOk) {
            Usuario comprador = repository.findByLogin("email@email.com").get();
            GatewayPagamento gateway = form.getGateway();

            Compra novaCompra = new Compra(produtoASerComprado, form.getQtde(), comprador, form.getGateway());
            manager.persist(novaCompra);

            return ResponseEntity.ok().body(novaCompra.toString());
        }

        BindException estoqueInsuficiente = new BindException(form, "form");
        estoqueInsuficiente.reject(null,
                "Quantidade requerida acima da quantidade em estoque! " +
                        "Quantidade em estoque: " + produtoASerComprado.getQtde());
        throw estoqueInsuficiente;
    }
}
