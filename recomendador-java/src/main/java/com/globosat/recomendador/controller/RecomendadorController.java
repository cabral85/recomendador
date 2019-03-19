/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.globosat.recomendador.controller;

import com.globosat.recomendador.entity.Produto;
import com.globosat.recomendador.entity.Visualizado;
import com.globosat.recomendador.motor.recomendacao.ProcessaRecomendacoes;
import com.globosat.recomendador.repository.RecomendadorRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Denis
 */
@RestController
@RequestMapping("v")
public class RecomendadorController {
    
    @Autowired
    RecomendadorRepository repositorio;
    
    @Autowired
    ProcessaRecomendacoes recomendacoes;
    
    @GetMapping("ping")
    public String ping(){
        return "Serviço online: " + LocalDateTime.now().toString();
    }
    
    @PostMapping("{idProduto}/view/{usuario}")
    public ResponseEntity<String> salvaVisualizado(@PathVariable("idProduto") Integer idProduto, @PathVariable("usuario") String usuario){
        Visualizado itemVisualizado = new Visualizado();
        itemVisualizado.setIdProduto(idProduto);
        itemVisualizado.setUsuario(usuario);
        repositorio.save(itemVisualizado);
        return ResponseEntity.accepted().build();
    }
    
    @GetMapping("similar")
    public List<Produto> buscaRecomendacoes(Integer itemVisualizado){
        return recomendacoes.buscaRecomendacao(itemVisualizado);
    }
}
