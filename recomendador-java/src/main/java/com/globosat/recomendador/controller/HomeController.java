/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globosat.recomendador.controller;

import com.globosat.recomendador.repository.RecomendadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Denis
 */
@RestController
@RequestMapping
public class HomeController {
    @Autowired
    RecomendadorRepository repositorio;
    
    @DeleteMapping
    public ResponseEntity<String> apagaVisualizados(){
        repositorio.deleteAll();
        return ResponseEntity.ok().build();
    }
}
