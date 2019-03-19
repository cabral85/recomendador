/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.globosat.recomendador.motor.recomendacao;

import com.globosat.recomendador.entity.MapReduceRecomendacoes;
import com.globosat.recomendador.entity.Produto;
import com.globosat.recomendador.entity.Visualizado;
import com.globosat.recomendador.repository.ProdutoRepository;
import com.globosat.recomendador.repository.RecomendadorRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Denis
 */
@Component
public class ProcessaRecomendacoes{
    List<MapReduceRecomendacoes> visualizacoes;
    
    @Autowired RecomendadorRepository recomendadorRepository;
    @Autowired ProdutoRepository produtoRepository;
    
    public List<Produto> buscaRecomendacao(Integer idProdutoVisualizado){
        if(idProdutoVisualizado != null)
        {
            List<Visualizado> visualizadosPorPessoa = new ArrayList<Visualizado>();
            List<Visualizado> recomendados = new ArrayList<Visualizado>();
            List<Produto> retorno = new ArrayList<Produto>();
            Produto produto = new Produto();
            //Com base no produto que o usuário visualizou busca pessoas que também tenham visualizado
            List<Visualizado> pessoasComMesmoInteresse = recomendadorRepository.findByIdProduto(idProdutoVisualizado);
            
            //Com base nas pessoas que buscou agora busque todos os produtos que essas pessoas viram
            pessoasComMesmoInteresse.forEach((visualizado) -> {
                visualizadosPorPessoa.addAll(recomendadorRepository.findByUsuario(visualizado.getUsuario()));
            });
            //Busca as ocorrencias totais de cada produto
            visualizadosPorPessoa.forEach((visualizado) -> {
                recomendados.addAll(recomendadorRepository.findByIdProduto(visualizado.getIdProduto()));
            });
            
            //Remove itens duplicados
            recomendados.stream().distinct();
            
            //Exclui item que já está sendo visualizado
            recomendados.removeIf(item -> (item.getIdProduto().equals(idProdutoVisualizado)));
            
            //Separe somente os produtos e ordene pelo id do produto para facilitar a contagem
            List<String> listaMap = mapeia(recomendados);
            
            //Contabilize cada incidencia por produto
            reduz(listaMap);
            
            //Ordene pela quantidade de vezes que os produtos foram visualizado
            visualizacoes.sort(Comparator.comparing(MapReduceRecomendacoes::getQtdOcorrencias).reversed());
            
            //Busca os dados dos produtos para retornar para loja
            for(MapReduceRecomendacoes item : visualizacoes){
                produto = new Produto();
                produto.setIdProduto(item.getIdProduto());
                produto.setUrl("www.globoplay.globo.com");
                retorno.add(produto);
            }
            
            //Valida se a lista contém 10 itens ou menos
            if(retorno.size() >= 10)
                return retorno.subList(0, 10);
            else
                return retorno;
        }else
        {
            return null;
        }
    }
    
    //Mapeia a lista, separa somente o produto para contagem
    public List<String> mapeia(List<Visualizado> visualizados){
        List<String> map = new ArrayList<String>();
        visualizados.forEach((visualizado) -> {
            map.add(visualizado.getIdProduto().toString());
        });
        Collections.sort(map);
        return map;
    }
    
    //Recebe os itens que foram mapeados e contabiliza
    public void reduz(List<String> maps){
        MapReduceRecomendacoes mapReduce = new MapReduceRecomendacoes();;
        visualizacoes = new ArrayList<MapReduceRecomendacoes>();
        String mapAnterior = "";
        Integer contador = 1;
        for(String map : maps){
            if(map.equals(mapAnterior)){
                mapAnterior = map;
                contador++;
            }
            else{
                if(mapAnterior.equals(""))
                    mapAnterior = map;
                mapReduce = new MapReduceRecomendacoes();
                mapReduce.setIdProduto(Integer.parseInt(mapAnterior));
                mapReduce.setQtdOcorrencias(contador);
                mapAnterior = map;
                contador = 1;
                visualizacoes.add(mapReduce);
            }
        }
    }
}
