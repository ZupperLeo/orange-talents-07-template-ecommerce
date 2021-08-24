package br.com.zup.mercado.livre.mercadolivre.dto;

import javax.validation.constraints.NotNull;

public class NotaFiscalCompraForm {

    @NotNull
    private Long compraId;
    @NotNull
    private Long compradorId;

    public NotaFiscalCompraForm(Long compraId, Long compradorId) {
        this.compraId = compraId;
        this.compradorId = compradorId;
    }

    @Override
    public String toString() {
        return "NotaFiscalCompraForm{" +
                "compraId=" + compraId +
                ", compradorId=" + compradorId +
                '}';
    }
}
