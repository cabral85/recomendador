/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globosat.recomendador.repository;

import com.globosat.recomendador.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Denis
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
    public Produto findByIdProduto(Integer idProduto);
}
