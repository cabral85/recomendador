/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globosat.recomendador.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Denis
 */
@Data
@Entity
@NoArgsConstructor
@IdClass(Visualizado.class)
public class Visualizado implements Serializable {
    @Id
    private String usuario;
    @Id
    private Integer idProduto;
}
