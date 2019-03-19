/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globosat.recomendador.repository;

import com.globosat.recomendador.entity.Visualizado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Denis
 */
@Repository
public interface RecomendadorRepository extends JpaRepository<Visualizado, String>{
    public List<Visualizado> findByUsuario(String Usuario);
    public List<Visualizado> findByIdProduto(Integer idProduto);
}
