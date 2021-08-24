package br.com.zup.mercado.livre.mercadolivre.dto;

import br.com.zup.mercado.livre.mercadolivre.model.Compra;
import br.com.zup.mercado.livre.mercadolivre.model.RetornoGatewayPagamento;
import br.com.zup.mercado.livre.mercadolivre.model.StatusTransacao;
import br.com.zup.mercado.livre.mercadolivre.model.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PaypalForm implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String transacaoId;

    public PaypalForm(int status, String transacaoId) {
        this.status = status;
        this.transacaoId = transacaoId;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        StatusTransacao resultado = this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;

        return new Transacao(resultado, transacaoId, compra);
    }
}
