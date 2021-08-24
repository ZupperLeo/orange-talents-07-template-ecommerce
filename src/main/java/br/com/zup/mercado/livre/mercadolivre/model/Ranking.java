package br.com.zup.mercado.livre.mercadolivre.model;

import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Ranking implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.isProcessadaComSucesso(), "A compra não foi concluida");

        RestTemplate template = new RestTemplate();
        Map<String, Object> request = Map.of("compraId", compra.getId(),
                "vendedorId", compra.getVendedor().getId());

        template.postForEntity("http://localhost:8080/ranking", request, String.class);

    }
}
