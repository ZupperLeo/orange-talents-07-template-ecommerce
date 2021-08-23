package br.com.zup.mercado.livre.mercadolivre.model;

import br.com.zup.mercado.livre.mercadolivre.dto.CaracteristicaDTO;
import br.com.zup.mercado.livre.mercadolivre.dto.Opinioes;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    private @NotNull BigDecimal valor;
    private @NotNull Integer qtde;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private @NotNull Set<Caracteristica> caracteristicas = new HashSet<>();
    private @NotBlank @Length(max = 1000) String descricao;
    private @NotNull @ManyToOne Categoria categoria;
    private LocalDateTime dataCadastro = LocalDateTime.now();
    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Imagem> imagens = new HashSet<Imagem>();
    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();

    @Deprecated
    public Produto() {}

    public Produto(@NotBlank String nome, @NotNull BigDecimal valor, @NotNull Integer qtde,
                   @NotNull @Valid @Size(min = 3) Collection<CaracteristicaDTO> caracteristicas,
                   @NotBlank @Length(max = 1000) String descricao, @NotNull Categoria categoria,
                   @NotNull @Valid Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.qtde = qtde;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas.addAll(caracteristicas.stream()
                        .map(caracteristica -> caracteristica.toModel(this))
                        .collect(Collectors.toSet()));
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", qtde=" + qtde +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", dataCadastro=" + dataCadastro +
                ", usuario=" + usuario +
                ", imagens=" + imagens +
                ", perguntas=" + perguntas +
                ", opinioes=" + opinioes +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
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

    public String getDescricao() {
        return descricao;
    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }

    public SortedSet<Pergunta> getPerguntas() {
        return perguntas;
    }

    public boolean pertenceAoUsuario(Usuario possivelUsuario) {
        return this.usuario.equals(possivelUsuario);
    }

    public boolean naoPerteenceAoUsuario(Usuario usario) {
        return this.usuario.equals(usuario);
    }

    public void associaImagens(Set<String> links) {
        Set<Imagem> imagens = links.stream()
                .map(link -> new Imagem(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public <T> Set<T> mapeiaCaracteristicas(
            Function<Caracteristica, T> funcaoMapeadora) {
        return this.caracteristicas.stream()
                .map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaImagens(
            Function<Imagem, T> funcaoMapeadora) {
        return this.imagens.stream()
                .map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(
            Function<Pergunta, T> funcaoMapeadora) {
        return this.perguntas.stream()
                .map(funcaoMapeadora)
                .collect(Collectors.toCollection(TreeSet :: new));
    }

    public boolean verificaEstoque(Integer qtde) {
        Assert.isTrue(qtde > 0, "Quantidade deve ser maior que zero");

        if(qtde <= this.qtde) {
            this.qtde -= qtde;
            return true;
        }

        return false;
    }
}
