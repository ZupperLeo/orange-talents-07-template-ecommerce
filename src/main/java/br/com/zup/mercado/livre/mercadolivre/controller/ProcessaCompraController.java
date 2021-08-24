package br.com.zup.mercado.livre.mercadolivre.controller;

import br.com.zup.mercado.livre.mercadolivre.config.service.EnventosNovaCompra;
import br.com.zup.mercado.livre.mercadolivre.dto.PagSeguroForm;
import br.com.zup.mercado.livre.mercadolivre.dto.PaypalForm;
import br.com.zup.mercado.livre.mercadolivre.model.Compra;
import br.com.zup.mercado.livre.mercadolivre.model.RetornoGatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProcessaCompraController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private EnventosNovaCompra enventosNovaCompra;

    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<String> fecharCompraPagSeguro(@PathVariable("id") Long compraId,
                                                   @Valid @RequestBody PagSeguroForm form) {
        return ResponseEntity.ok().body(processa(compraId, form));
    }

    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<String> fecharCompraPayapal(@PathVariable("id") Long compraId,
                                                   @Valid @RequestBody PaypalForm form) {
        return ResponseEntity.ok().body(processa(compraId, form));
    }

    private String processa(Long compraId, RetornoGatewayPagamento gateway) {
        Compra compra = manager.find(Compra.class, compraId);
        compra.addTransacao(gateway);
        manager.merge(compra);
        enventosNovaCompra.processa(compra);

        return compra.toString();
    }
}
