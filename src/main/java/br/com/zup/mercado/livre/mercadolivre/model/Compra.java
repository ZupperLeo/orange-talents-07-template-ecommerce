package br.com.zup.mercado.livre.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private Produto produtoASerComprado;
    @NotNull
    @Positive
    private Integer qtde;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;
    @Enumerated
    @NotNull
    private GatewayPagamento gateway;

    public Compra(@NotNull @Valid Produto produtoASerComprado, @NotNull @Positive Integer qtde,
                  @NotNull @Valid Usuario comprador, @NotNull GatewayPagamento gateway) {
        this.produtoASerComprado = produtoASerComprado;
        this.qtde = qtde;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoASerComprado=" + produtoASerComprado +
                ", qtde=" + qtde +
                ", comprador=" + comprador +
                ", gateway=" + gateway +
                '}';
    }
}
