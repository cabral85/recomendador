from collections import OrderedDict
from ProdutoDAO import ProdutoDAO

dao = ProdutoDAO()

def sugereRecomendacao(produtoSimilar):
    usuariosSimilares = []
    produtosFiltro = []
    produtosRecomendados = []
    #Busca usuario similar
    for item in dao.get___listaProduto():
        usuario = item[0]
        produto = item[1]
        if(produtoSimilar == produto):
            usuariosSimilares.append(usuario)

    #Busca itens dos usuarios similares
    for item in dao.get___listaProduto():
        usuario = item[0]
        produto = item[1]
        if(produto != produtoSimilar):
            if(validaLista(usuariosSimilares, usuario)):
                produtosFiltro.append(produto)

    #Da um distinct na lista
    lista_distinta = list(set(produtosFiltro))

    #Pega o total de ocorrencias por produto
    for item in dao.get___listaProduto():
        produto = item[1]
        if(validaLista(lista_distinta, produto)):
            produtosRecomendados.append(produto)

    #Retorna 10 recomendacoes caso exista 10, caso seja menos ja retorna valor menor
    return mapReduce(sorted(produtosRecomendados))[:10]

def validaLista(lista, valor):
    retorno = False
    for item in lista:
        if item == valor:
            retorno = True
    return retorno

#Mapeia os valores, contabiliza, reduz e retorna o modelo json [{url: www.globoplay.globo.com/v/(item-recomendado)},....]
def mapReduce(listaTextos):
    textoAnterior = None
    contador = 0
    listaContador = {}
    indice = 0
    tamanho_indice = len(listaTextos)
    retorno = []

    for texto in listaTextos:
        if textoAnterior == None:
                textoAnterior = texto
        if textoAnterior == texto:
            contador += 1
        if (tamanho_indice-1) == indice:
            key = texto
            value = contador
            listaContador[key] = value
        else:
            key = textoAnterior
            value = contador
            listaContador[key] = value
            contador = 1
            textoAnterior = texto
        indice += 1
    itens_ordernados = sorted(listaContador.items(), key=lambda kv: kv[1], reverse=True)
    for item in itens_ordernados:
        retorno.append({"url": "www.globoplay.globo.com/v/" + item[0]})
    return retorno
    
