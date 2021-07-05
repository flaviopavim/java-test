banco de dados feito em mysql
login: root
senha: 123456

Salvar produto
Feito em método post
exemplo de dados de post:
{
"id":3,
"title":"produto teste 3",
"price":3333.33
}

url do post
localhost:8080/api/product/save

Ler produto
/api/product/id_produto
localhost:8080/api/product/1

Consultar taxa
Feito em método get pra diferenciar
/api/tax/id_produto/primeira_parcela/parcelas
localhost:8080/api/tax/1/100/6
