package br.com.zup.mercado.livre.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotNull StatusTransacao status;
    private @NotBlank String transacaoGatewayId;
    private @NotNull LocalDateTime dataTransacao = LocalDateTime.now();
    @ManyToOne
    private @NotNull @Valid Compra compra;

    @Deprecated
    public Transacao() {}

    public Transacao(@NotNull StatusTransacao status, @NotBlank String transacaoGatewayId,
                     @NotNull @Valid Compra compra) {
        this.status = status;
        this.transacaoGatewayId = transacaoGatewayId;
        this.compra = compra;
    }

    public boolean isConcluida() {
        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((transacaoGatewayId == null) ? 0 : transacaoGatewayId.hashCode());
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
        Transacao other = (Transacao) obj;
        if (transacaoGatewayId == null) {
            if (other.transacaoGatewayId != null)
                return false;
        } else if (!transacaoGatewayId.equals(other.transacaoGatewayId))
            return false;
        return true;
    }
}
