package com.ildenio.curso.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ildenio.curso.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;

    @Entity
    @JsonTypeName(value = "pagamentoComBoleto")
    public class PagamentoComBoleto extends Pagamento {
        @JsonFormat(pattern = "dd/MM/yyyy")
        private Date dataVencimento;
        @JsonFormat(pattern = "dd/MM/yyyy")
        private Date dataPagamento;



    public PagamentoComBoleto(){}

        @JsonCreator
        public PagamentoComBoleto(@JsonProperty("id") Integer id,@JsonProperty("estado") EstadoPagamento estado,@JsonProperty("pedido") Pedido pedido,@JsonProperty("dataVencimento") Date dataVencimento,@JsonProperty("dataPagamento") Date dataPagamento) {
            super(id, estado, pedido);
            this.dataVencimento = dataVencimento;
            this.dataPagamento = dataPagamento;
        }



        public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;


    }







}
