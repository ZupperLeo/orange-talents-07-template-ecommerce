package br.com.zup.mercado.livre.mercadolivre.dto;

import br.com.zup.mercado.livre.mercadolivre.model.Pergunta;
import br.com.zup.mercado.livre.mercadolivre.model.Produto;
import br.com.zup.mercado.livre.mercadolivre.model.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class PerguntaForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;

    public PerguntaForm(@NotBlank String titulo, @NotBlank String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Pergunta toModel(@Valid Usuario usuario, @Valid Produto produto) {
        return new Pergunta(this.titulo, this.descricao, usuario, produto);
    }
}
