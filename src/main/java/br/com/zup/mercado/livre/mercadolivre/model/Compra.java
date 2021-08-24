package br.com.zup.mercado.livre.mercadolivre.model;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {}

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

    public Usuario getComprador() {
        return comprador;
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

    public Usuario getVendedor() {
        return produtoASerComprado.getUsuario();
    }

    public String urlRedirecionamento(UriComponentsBuilder url) {
        return this.gateway.urlRetorno(this, url);
    }

    public boolean isProcessadaComSucesso() {
        return !transacoesConcluidas().isEmpty();
    }

    private Set<Transacao> transacoesConcluidas() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::isConcluida)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,
                "Foi encontrado mais de uma transação concluida com sucesso nesse id");

        return transacoesConcluidasComSucesso;
    }

    public void addTransacao(@Valid RetornoGatewayPagamento form) {
        Transacao transacao = form.toTransacao(this);

        /*(1)Valida se nao ha uma mesma transacao no bd
        * (2)Valida se a compra foi conlcuida*/
        Assert.state(!this.transacoes.contains(transacao), "Já existe uma transação igual a essa!");//1
        Assert.state(transacoesConcluidas().isEmpty(), "Essa compra já foi concluida");//2

        this.transacoes.add(transacao);
    }
}
