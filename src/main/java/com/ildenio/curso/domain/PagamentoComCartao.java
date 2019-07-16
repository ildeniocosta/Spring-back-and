package com.ildenio.curso.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ildenio.curso.domain.enums.EstadoPagamento;

import javax.persistence.Entity;

@Entity
@JsonTypeName(value = "pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{
    private Integer numeroDeParcelas;

    public PagamentoComCartao(){}

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

}
