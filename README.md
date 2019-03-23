# recomendador
Soluções escrita em Java e Python, mesmo problema, porém utilizado a mesma solução em linguagens diferentes.

Essa API é um recomendador de produtos, utilizei um pouco o conceito de MapReduce do Hadoop para criar a lógica de
receber N strings, ordena-las, conta-las e agrupa-las.
Com isso consegui fazer com que:
teste, teste, teste, auau, auau, uaua, uaua, uaua, uaua
Vira-se algo assim:
uaua: 4, teste: 3, auau: 2

E com isso consegui retornar em ordem de maior visualização os produtos mais visualizados, e claro,
levando em consideração visualizações similares, ou seja, um usuário acabou de olhar um determinado produto,
eu busco com base nesse produto outras pessoas que tenham o visto, e depois pego outros produtos que elas viram e 
faço a lógica do MapReduce para sugerir com base nessas pessoas.
