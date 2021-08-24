package br.com.zup.mercado.livre.mercadolivre.model;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso{

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.isProcessadaComSucesso(), "A compra não foi concluida");

        RestTemplate template = new RestTemplate();
        Map<String, Object> request = Map.of("compraId", compra.getId(),
                "compradorId", compra.getComprador().getId());

        template.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);

    }
}
