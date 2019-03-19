## Denis Cabral Lopes

Documentação
============

Nesse projeto utilizei a linguagem Java com o framework open-source Spring.
Escolhi esse framework por ser mais um desafio para mim, por estar trabalhando com ele a pouco tempo,
então aproveitei a oportunidade para tentar utiliza-lo.

Criei 2 endpoints, um deles seguindo o padrão proposto de prefixo "www.globoplay.com" e outro sem prefixo.
No endpoint "Home" (sem prefixo) eu adicionei a chamada para apagar todas as visualizações de documentos.
No endpoint "www.globoplay.com" eu adicionei as chamadas de POST onde é adicionado a visualização de um documento e
a chamada de GET, na POST foi adicionado o sufixo "VIEW" e na de GET "SIMILAR"

Eu utilizei um padrão de projetos chamado de Repository, é um padrão bem utilizado e conhecido, ele indica basicamente o
repositório de dados do seu serviço, em alguns casos ele é utilizado em conjunto com o DAO também onde é feito a persistencia em bancos de dados.

Os dados de visualização ficam salvos em um banco H2 embutido ao framework que trabalha bem em conjunto ao Spring.
Para a persistencia eu utilizei um padrão do Spring que extendo a biblioteca JpaRepository e passo para ela a entidade e o datatype da chave primária.
Utilizando esse padrão não preciso me preocupar em criar implementações da interface, pois o Spring as cria automaticamente, assim agilizando o processo
de trabalho.

Nas interfaces criei alguns métodos que também são implementados de forma automática, só preciso seguir um padrão para criação do nome dos métodos, sendo:
findBy (para buscar algo), deleteBy (para deletar) e assim por diante, após esse prefixo eu posso adicionar o nome da coluna (atributo) ou classe, em caso 
de chave composta.

Está implementado também o swagger com a documentação do REST, http://localhost:8080/swagger-ui.html


## Desafios

Eu gostaria de ter utilizado o Mahout com o Java, mas optei por não utiliza-lo para demonstrar o conceito de MapReduce que ele utilizaria de forma automática,
assim eu implementei métodos que fazem esse trabalhando mapeando os dados e categorizando-os.

Eu também gostaria de poder ter implementado o SpringSecurity, ele faria toda a parte de validação de usuário, etc., porém, creio que isso prolongaria um pouco 
mais o desenvolvimento.

## Não Implementado

* Desculpem não implementei a parte de chamada do curl -d "user=user1", pois não sei como é essa passagem de parâmetro, eu busquei na internet, porém não achei, devo ter procurado errado.
* Não consegui criar o .configure, apenas o make, entendi que o .configure faz o trabalho de validar as depencias, etc., mas ainda não consegui entender o funcionamento dele o suficiente para utiliza-lo.
* Para o deploy em diversos servidores só tenho como experiência utilizar scripts shell com chaves ssh ou o Jenkins que também vai utilizar shell script ou script python, só encontrei o Ansible, mas ainda não consegui trabalhar com ele.
* Caso eu receba a oportunidade, conseguirei me ajustar rapidamente e adquirir o conhecimento que falta

## Testes

Realizei testes alimentando as visualizações e depois validando as recomendações
