package br.com.zup.mercado.livre.mercadolivre.config.service;

import br.com.zup.mercado.livre.mercadolivre.model.Compra;
import br.com.zup.mercado.livre.mercadolivre.model.EventoCompraSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EnventosNovaCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;

    public void processa(Compra compra) {
        if (compra.isProcessadaComSucesso()) {
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        }
    }
}
