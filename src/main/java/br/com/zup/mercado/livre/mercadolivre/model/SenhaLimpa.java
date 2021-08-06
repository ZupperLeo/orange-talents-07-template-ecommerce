package br.com.zup.mercado.livre.mercadolivre.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Representa uma senha antes de ser criptografada
 * @author leonardo.araujo
 */

public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
        Assert.isTrue(senha.length() >= 6, "A senha precisa ter no minimo 6 caracteres");
        Assert.hasLength(senha, "A senha não pode estar em branco");

        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
