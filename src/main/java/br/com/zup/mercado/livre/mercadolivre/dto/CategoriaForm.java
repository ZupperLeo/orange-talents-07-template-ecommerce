package br.com.zup.mercado.livre.mercadolivre.dto;

import br.com.zup.mercado.livre.mercadolivre.model.Categoria;
import br.com.zup.mercado.livre.mercadolivre.config.validator.ExistId;
import br.com.zup.mercado.livre.mercadolivre.config.validator.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaForm {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @Positive
    @ExistId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaMaeId;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoriaMaeId(Long categoriaMaeId) {
        this.categoriaMaeId = categoriaMaeId;
    }


    public Categoria toModel(EntityManager manager) {
        Categoria categoria =  new Categoria(nome);
        if(categoriaMaeId != null) {
            Categoria categoriaMae = manager.find(Categoria.class, categoriaMaeId);
            Assert.notNull(categoriaMae, "Id não exite no banco de dados!");

            categoria.setCategoriaMae(categoriaMae);
        }

        return categoria;
    }
}
