/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.globosat.recomendador;

/**
 *
 * @author Denis
 */
import com.globosat.recomendador.entity.Produto;
import com.globosat.recomendador.entity.Visualizado;
import com.globosat.recomendador.motor.recomendacao.ProcessaRecomendacoes;
import com.globosat.recomendador.repository.RecomendadorRepository;
import com.globosat.recomendador.utils.RandomString;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestaRecomendacoes {
    
    @Autowired
            ProcessaRecomendacoes motorRecomendacoes;
    
    @Autowired
            RecomendadorRepository repositorio;
    
    private Visualizado visualizado;
    private List<Visualizado> visualizados;
    
    /***
     * Alimenta valores de visualização de produtos para testar recomendações
     * calculadas
     */
    @Before
    public void alimentaDados(){
        visualizados = new ArrayList<Visualizado>();
        //usuario 1
        for(int i = 1; i <= 20; i++){
            visualizado = new Visualizado();
            visualizado.setIdProduto(i);
            visualizado.setUsuario("usuario1");
            repositorio.save(visualizado);
        }
        //usuario 2
        for(int i = 1; i <= 20; i++){
            visualizado = new Visualizado();
            visualizado.setIdProduto(i);
            visualizado.setUsuario("usuario2");
            visualizados.add(visualizado);
            repositorio.save(visualizado);
        }
        //usuario 3 - pares
        for(int i = 1; i <= 20; i++){
            if(i%2==0)
            {
                visualizado = new Visualizado();
                visualizado.setIdProduto(i);
                visualizado.setUsuario("usuario3");
                visualizados.add(visualizado);
                repositorio.save(visualizado);
            }
        }
        //usuario 4
        visualizado = new Visualizado();
        visualizado.setIdProduto(20);
        visualizado.setUsuario("usuario4");
        visualizados.add(visualizado);
        
        visualizados.forEach((itens) -> {repositorio.save(itens);});
    }
    
    @Test
    public void validaRecomendacoes(){
        List<Produto> recomendacoes = motorRecomendacoes.buscaRecomendacao(1);
        assertTrue(recomendacoes.size() == 10);
    }
    
    //Como a maioria dos itens são pares, valido se trouxe as recomendações certas
    @Test
    public void validaRecomendacoesPares(){
        List<Produto> recomendacoes = motorRecomendacoes.buscaRecomendacao(1);
        Boolean retorno = true;
        for(Produto produto : recomendacoes){
            if(produto.getIdProduto() % 2 != 0)
                retorno = false;
        }
        assertTrue(retorno);
    }
}
