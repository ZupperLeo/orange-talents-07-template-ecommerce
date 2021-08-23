package br.com.zup.mercado.livre.mercadolivre.model;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    pagseguro {
        @Override
        String urlRetorno(Compra compra,
                          UriComponentsBuilder componentsBuilder) {
            String urlPagseguro = componentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId())
                    .toString();

            return "pagseguro.com/" + compra.getId() + "?redirectUrl+"
                    + urlPagseguro;
        }
    },

    paypal {
        @Override
        String urlRetorno(Compra compra,
                          UriComponentsBuilder componentsBuilder) {
            String urlPaypal = componentsBuilder
                    .path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId())
                    .toString();

            return "paypal.com/" + compra.getId() + "?redirectUrl+"
                    + urlPaypal;
        }
    };

    abstract String urlRetorno(Compra compra,
                               UriComponentsBuilder componentsBuilder);
}
