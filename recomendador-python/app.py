from flask import Flask
from flask import request
from MapReduce import sugereRecomendacao
from flask import json
from ProdutoDAO import ProdutoDAO

app = Flask(__name__)
produtoDAO = ProdutoDAO()

@app.route('/v/<produto>/view', methods=['POST'])
def salvaVisualizacao(produto):
    user = request.form['user']
    produtoDAO.set___listaProduto([user, produto])
    response = app.response_class(
        status=200,
        mimetype='application/json'
    )
    return response

@app.route('/ping', methods=['GET'])
def ping():
    return "Funcionando!"

@app.route('/v/<produto>/similar', methods=['GET'])
def similar(produto):
    retorno = sugereRecomendacao(produto)
    response = app.response_class(
        response=json.dumps(retorno),
        status=200,
        mimetype='application/json'
    )
    return response


@app.route('/', methods=['DELETE'])
def apagaVisualizacoes():
    produtoDAO.set___limpaLista()
    response = app.response_class(
        response=json.dumps("Itens deletados"),
        status=200,
        mimetype='application/json'
    )
    return response

app.run(host='0.0.0.0', port=8080)
