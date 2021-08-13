package br.com.zup.mercado.livre.mercadolivre.dto;

import br.com.zup.mercado.livre.mercadolivre.config.validator.MinimumValue;
import br.com.zup.mercado.livre.mercadolivre.model.Categoria;
import br.com.zup.mercado.livre.mercadolivre.model.Produto;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoForm {

    @NotBlank
    private String nome;
    @NotNull
    @MinimumValue(domainClass = Produto.class, fieldName = "valor")
    private BigDecimal valor;
    @NotNull
    @Min(0)
    private Integer qtde;
    @NotNull
    private List<CaracteristicaDTO> caracteristicas = new ArrayList<>();
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    private Long categoriaId;
    private LocalDateTime dataCadastro;

    @Deprecated
    public ProdutoForm() {}

    public ProdutoForm(@NotBlank String nome, @NotNull BigDecimal valor, @NotNull Integer qtde,
                       @NotNull List<CaracteristicaDTO> caracteristicas, @NotBlank @Length(max = 1000) String descricao,
                       @NotNull Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.qtde = qtde;
        this.caracteristicas.addAll(caracteristicas);
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.dataCadastro = LocalDateTime.now();;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQtde() {
        return qtde;
    }

    public List<CaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaDTO> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Produto toModel(EntityManager manager) {
        Categoria categoria = manager.find(Categoria.class, categoriaId);
        return new Produto(nome, valor, qtde, caracteristicas, descricao, categoria);
    }
}
