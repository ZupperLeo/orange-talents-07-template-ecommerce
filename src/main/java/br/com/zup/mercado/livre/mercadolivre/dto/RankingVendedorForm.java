package br.com.zup.mercado.livre.mercadolivre.dto;

import javax.validation.constraints.NotNull;

public class RankingVendedorForm {

    @NotNull
    private Long compraId;
    @NotNull
    private Long vendedorId;

    public RankingVendedorForm(Long compraId, Long vendedorId) {
        this.compraId = compraId;
        this.vendedorId = vendedorId;
    }

    @Override
    public String toString() {
        return "RankingVendedorForm{" +
                "compraId=" + compraId +
                ", vendedorId=" + vendedorId +
                '}';
    }
}
