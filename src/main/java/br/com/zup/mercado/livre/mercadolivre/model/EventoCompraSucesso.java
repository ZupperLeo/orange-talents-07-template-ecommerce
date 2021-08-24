package br.com.zup.mercado.livre.mercadolivre.model;

public interface EventoCompraSucesso {

    /**
     * Qualquer evento relacioando ao sucesso de uma nova compra deve implementar
     * essa interface
     */
    void processa(Compra compra);
}
