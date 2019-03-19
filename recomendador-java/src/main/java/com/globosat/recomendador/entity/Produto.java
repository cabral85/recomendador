/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globosat.recomendador.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author denis.lopes
 */
@Data
@Entity
@NoArgsConstructor
public class Produto implements Serializable {
       
    @Id
    private Integer idProduto;
    private String url;
}
