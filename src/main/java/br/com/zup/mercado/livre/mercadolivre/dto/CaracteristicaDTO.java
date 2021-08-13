package br.com.zup.mercado.livre.mercadolivre.dto;

import br.com.zup.mercado.livre.mercadolivre.model.Caracteristica;
import br.com.zup.mercado.livre.mercadolivre.model.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaDTO {

    @NotBlank
    private String nome;
    @NotBlank String descricao;

    @Deprecated
    public CaracteristicaDTO() {}

    public CaracteristicaDTO(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Caracteristica toModel(@NotNull @Valid Produto produto) {
        return new Caracteristica(this.nome, this.descricao, produto);
    }
}
