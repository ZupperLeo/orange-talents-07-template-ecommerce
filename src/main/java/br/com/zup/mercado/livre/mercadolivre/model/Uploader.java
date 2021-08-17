package br.com.zup.mercado.livre.mercadolivre.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface Uploader {

    /**
     *
     * @param imagens
     * @return links para imagens que foram uploadadas
     */
    Set<String> envia(List<MultipartFile> imagens);
}
