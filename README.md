# apiMarketplace
Api Marketplace Luiza Labs

Código Fonte relativo ao Projeto desenvolvido em Java 8 e Maven

Foi utilizado o Framework Spring Boot

Objetivo da Api: 
Receber uma lista de produtos no formato json, processá-la e devolver uma resposta, conforme opções de agrupamento, ordenação e filtro.

Para rodar o projeto:

Instalar o MySQL

Criar banco chamado mktplace

create schema mktplace;

Definir user : 'root'   password : '' (vazio) ou alterar os dados no arquivo properties em src/main/resources

Executar o script do arquivo sql/produtos.sql

Acessar a api atravé do EndPoint: localhost:8080/marketplace/api/produtos/

Parametros opcionais: 
orderby filter

O arquivo .jar está disponível no link abaixo:

https://drive.google.com/file/d/0B-0hs_HjmA9OYjU1azZydjVOVHc/view?usp=sharing

Os Testes automatizados podem ser executados através do comando:  mvn test
