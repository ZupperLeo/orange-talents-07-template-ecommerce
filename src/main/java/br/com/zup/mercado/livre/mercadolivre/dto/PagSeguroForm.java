package br.com.zup.mercado.livre.mercadolivre.dto;

import br.com.zup.mercado.livre.mercadolivre.model.Compra;
import br.com.zup.mercado.livre.mercadolivre.model.RetornoGatewayPagamento;
import br.com.zup.mercado.livre.mercadolivre.model.StatusRetornoPagSeguro;
import br.com.zup.mercado.livre.mercadolivre.model.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagSeguroForm implements RetornoGatewayPagamento {

    @NotBlank
    private String transacaoId;
    @NotNull
    private StatusRetornoPagSeguro status;

    public PagSeguroForm(@NotBlank String transacaoId, StatusRetornoPagSeguro status) {
        this.transacaoId = transacaoId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagSeguroForm{" +
                "transacaoId='" + transacaoId + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), transacaoId, compra);
    }

}
