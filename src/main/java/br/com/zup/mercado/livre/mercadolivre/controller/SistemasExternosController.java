package br.com.zup.mercado.livre.mercadolivre.controller;

import br.com.zup.mercado.livre.mercadolivre.dto.NotaFiscalCompraForm;
import br.com.zup.mercado.livre.mercadolivre.dto.RankingVendedorForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Esse controller tem como funcionalidade,
 * simular sistemas externos que interajam com a API do mercado livre
 * */
@RestController
public class SistemasExternosController {

    @PostMapping(value = "/notas-fiscais")
    public void criaNotaFiscal(@Valid @RequestBody NotaFiscalCompraForm form) {
        System.out.println("Nota fiscal criada!");
    }

    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody RankingVendedorForm form) {
        System.out.println("Ranking criado!");
    }
}
