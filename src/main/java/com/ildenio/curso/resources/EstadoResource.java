package com.ildenio.curso.resources;

import com.ildenio.curso.domain.Cidade;
import com.ildenio.curso.domain.Estado;
import com.ildenio.curso.dto.CidadeDTO;
import com.ildenio.curso.dto.EstadoDTO;
import com.ildenio.curso.services.CidadeService;
import com.ildenio.curso.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/estados")
public class EstadoResource {
    @Autowired
    private EstadoService estadoService;
    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<EstadoDTO>>findAll(){
        List<Estado> list = estadoService.findAll();
        List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @RequestMapping(value = "/{estadoId}/cidades",method = RequestMethod.GET)
    ResponseEntity<List<CidadeDTO>>findCidades(@PathVariable Integer estadoId){
        List<Cidade> list = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
