package com.ildenio.curso.services;

import com.ildenio.curso.domain.ItemPedido;
import com.ildenio.curso.domain.PagamentoComBoleto;
import com.ildenio.curso.domain.Pedido;
import com.ildenio.curso.domain.enums.EstadoPagamento;
import com.ildenio.curso.repositories.ItemPedidoRepository;
import com.ildenio.curso.repositories.PagamentoRepository;
import com.ildenio.curso.repositories.PedidoRepository;
import com.ildenio.curso.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private PedidoRepository repo;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! id: "+id+" ,Tipo: "+Pedido.class.getName()));
    }
    public Pedido insert(Pedido obj){

        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for(ItemPedido ip : obj.getItens()){
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
          return obj;
    }

    }

