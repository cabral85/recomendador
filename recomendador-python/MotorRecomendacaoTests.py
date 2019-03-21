from MapReduce import sugereRecomendacao
from MapReduce import mapReduce
import unittest
from ProdutoDAO import ProdutoDAO

dao = ProdutoDAO()

retorno = []
class Testes(unittest.TestCase):
    def retornoMapReduceTest(self):
        lista = ['11']
        retorno = mapReduce(lista)
        self.assertEqual(retorno[0], {"url": "v/11"})

    def validaMaisDeUmRetorno(self):
        lista = ['12','13']
        validaRetorno = [{"url": "v/12"}, {"url": "v/13"}]
        retorno = mapReduce(lista)
        self.assertListEqual(validaRetorno, retorno)

    def validaItensSalvos(self):
        lista = ['11','12','13']
        dao.set___listaProduto(lista)
        self.assertListEqual(dao.getListaProduto[0], lista)

    def validaRecomendacao(self):
        dao.set___limpaLista()
        validaRetorno = [{"url": "v/3"}]
        dao.set___listaProduto(['user1','5'])
        dao.set___listaProduto(['user1','1'])
        dao.set___listaProduto(['user2','1'])
        dao.set___listaProduto(['user2','3'])
        dao.set___listaProduto(['user3','3'])
        dao.set___listaProduto(['user6','3'])
        dao.set___listaProduto(['user3','2'])
        dao.set___listaProduto(['user4','2'])
        recomendacoes = sugereRecomendacao('2')
        self.assertListEqual(validaRetorno,recomendacoes)

executor = Testes()
executor.retornoMapReduceTest()
executor.validaMaisDeUmRetorno()
executor.validaItensSalvos()
executor.validaRecomendacao()
