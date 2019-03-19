/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globosat.recomendador.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Denis
 */
@Data
@NoArgsConstructor
public class MapReduceRecomendacoes implements Serializable{
    private Integer idProduto;
    private Integer qtdOcorrencias;
}
