package br.com.zup.mercado.livre.mercadolivre.config.service;

import br.com.zup.mercado.livre.mercadolivre.model.Pergunta;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class Mail {

    private String email;
    private final String emailML = "email@email.com";
    private String mensagem;
    private String assunto = "Você possui uma nova mensagem: ";

    public String send(@NotNull @Valid Pergunta pergunta) {
        this.email = pergunta.getUsuario().getLogin();
        return this.assunto + pergunta.getDescricao();
    }
}
