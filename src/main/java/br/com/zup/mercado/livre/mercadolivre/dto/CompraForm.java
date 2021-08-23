package br.com.zup.mercado.livre.mercadolivre.dto;

import br.com.zup.mercado.livre.mercadolivre.model.GatewayPagamento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraForm {

    @Positive
    private Integer qtde;
    @NotNull
    private Long produtoId;
    @NotNull
    private GatewayPagamento gateway;

    public CompraForm(@Positive Integer qtde, @NotNull Long produtoId,
                      @NotNull GatewayPagamento gateway) {
        this.qtde = qtde;
        this.produtoId = produtoId;
        this.gateway = gateway;
    }

    public Integer getQtde() {
        return qtde;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
