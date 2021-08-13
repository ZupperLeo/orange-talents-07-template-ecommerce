package br.com.zup.mercado.livre.mercadolivre.model;

import br.com.zup.mercado.livre.mercadolivre.dto.CaracteristicaDTO;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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
    private @NotBlank String descricao;
    private @NotNull @ManyToOne Categoria categoria;
    private LocalDateTime dataCadastro;

    public Produto(@NotBlank String nome, @NotNull BigDecimal valor, @NotNull Integer qtde,
                   @NotNull @Valid Collection<CaracteristicaDTO> caracteristicas, @NotBlank String descricao,
                   @NotNull Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.qtde = qtde;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas.addAll(caracteristicas.stream()
                        .map(caracteristica -> caracteristica.toModel(this))
                        .collect(Collectors.toSet()));
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
}
