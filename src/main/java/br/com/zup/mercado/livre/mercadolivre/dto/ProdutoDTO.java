package br.com.zup.mercado.livre.mercadolivre.dto;

import br.com.zup.mercado.livre.mercadolivre.model.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

/**
 * Desenvolvi essa classe para relacionar todos os itens de um produto
 * em uma unica classe simulando um produto "real"
 */
public class ProdutoDTO {

    private String nome;
    private BigDecimal valor;
    private Integer qtde;
    private Set<CaracteristicaDTO> caracteristicas = new HashSet<>();
    private String descricao;
    private Set<String> imagens = new HashSet<>();
    private Set<Map<String, String>> opinioes = new HashSet<>();
    private Double mediaNota;
    private Integer totalNotas;
    private SortedSet<String> perguntas;

    public ProdutoDTO(Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.qtde = produto.getQtde();
        this.caracteristicas = produto.mapeiaCaracteristicas(CaracteristicaDTO::new);
        this.imagens = produto.mapeiaImagens(imagem -> imagem.getLink());//Retorna apenas os links das imagens
        this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());//TODO trazer o conteudo da pergunta alem do titulo

        Opinioes opinioes = produto.getOpinioes();
        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
        });

        this.mediaNota = opinioes.media();
        this.totalNotas = opinioes.total();
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

    public Set<CaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public Double getMediaNota() {
        return mediaNota;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }
}
