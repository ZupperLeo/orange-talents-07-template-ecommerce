package br.com.zup.mercado.livre.mercadolivre.dto;

import br.com.zup.mercado.livre.mercadolivre.model.Opiniao;
import br.com.zup.mercado.livre.mercadolivre.model.Produto;
import br.com.zup.mercado.livre.mercadolivre.model.Usuario;

import javax.validation.constraints.*;

public class OpiniaoForm {

    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @Deprecated
    public OpiniaoForm() {
    }

    public OpiniaoForm(@NotBlank String titulo, @NotBlank @Size(max = 500) String descricao,
                       @NotNull @Min(1) @Max(5) Integer nota) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public Opiniao toModel(Produto produto, Usuario usuario) {
        return new Opiniao(titulo, descricao, nota, usuario, produto);
    }
}
