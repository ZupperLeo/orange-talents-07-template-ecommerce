package br.com.zup.mercado.livre.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta implements Comparable<Pergunta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String titulo;
    private @NotBlank String descricao;
    private @NotNull LocalDateTime momentoPerguntaFeita = LocalDateTime.now();
    @ManyToOne
    private @NotNull @Valid Usuario usuario;
    @ManyToOne
    private @NotNull @Valid Produto produto;

    @Deprecated
    public Pergunta() {}

    public Pergunta(@NotBlank String titulo, @NotBlank String descricao, @Valid Usuario usuario,
                    @Valid Produto produto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((usuario == null) ? 0 : usuario.hashCode());
        result = prime * result + ((produto == null) ? 0 : produto.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
        Pergunta other = (Pergunta) obj;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        if (produto == null) {
            if (other.produto != null)
                return false;
        } else if (!produto.equals(other.produto))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        return true;
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }
}
