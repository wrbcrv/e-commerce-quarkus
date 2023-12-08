# GLWare - Back-End (Java / Quarkus)

Olá! seja bem vindo(a) ao código back-end do "GLWare: E-Commerce de Peças de Hardware". Este repositório contém o código fonte e a lógica do servidor desenvolvido com o framework Quarkus e linguagem de Programação Java. Este projeto foi criado para a disciplina de Tópicos em Programação II do curso Bacharelado de Sistemas de Informação pela Universidade Estadual do Tocantins - UNITINS.

## Descrição

Este projeto consiste na base do sistema back-end do GLWare, responsável pelo gerenciamento e oferta de funcionalidades para o e-commerce de peças de hardware. Ele inclui módulos para:

- Gerenciamento de Usuário;
- Gerenciamento de Produto;
- Cupons e Descontos;
- Descrições com imagens;
- Gerenciamento de Fornecedor;
- Gerencimanento de Marca;
- Gerenciamento e Processamento de Pedidos.

## Executando a aplicação no modo de desenvolvimento

Você pode executar sua aplicação no modo de desenvolvimento, que permite codificação ao vivo usando:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTA:_**  O Quarkus agora vem com uma Interface de Desenvolvedor (Dev UI), disponível apenas no modo de desenvolvimento em http://localhost:8080/q/dev/.


## Empacotando e executando a aplicação

A aplicação pode ser empacotada usando:
```shell script
./mvnw package
```
Isso produz o arquivo quarkus-run.jar no diretório target/quarkus-app/.
Lembre-se de que não é um über-jar porque as dependências são copiadas para o diretório target/quarkus-app/lib/.

A aplicação agora pode ser executada usando `java -jar target/quarkus-app/quarkus-run.jar`.

Se desejar construir um über-jar, execute o seguinte comando:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

A aplicação, empacotada como um über-jar, pode ser executada usando `java -jar target/*-runner.jar`.

## Criando um executável nativo

Você pode criar um executável nativo usando: 
```shell script
./mvnw package -Pnative
```

Ou, se não tiver o GraalVM instalado, pode executar a construção do executável nativo em um contêiner usando: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

Em seguida, pode executar seu executável nativo com: `./target/topicos2-2023-2-1.0.0-SNAPSHOT-runner`

Se quiser aprender mais sobre a criação de executáveis nativos, consulte https://quarkus.io/guides/maven-tooling.

## Guias Relacionados

- SmallRye OpenAPI ([guia](https://quarkus.io/guides/openapi-swaggerui)): Documente suas APIs REST com OpenAPI - inclui o Swagger UI.
- Hibernate ORM with Panache ([guia](https://quarkus.io/guides/hibernate-orm-panache)): Simplifique seu código de persistência para o Hibernate ORM via active record ou o padrão de repositório.
- RESTEasy Classic ([guia](https://quarkus.io/guides/resteasy)): Framework de endpoints REST que implementa Jakarta REST e mais.
- JDBC Driver - PostgreSQL ([guia](https://quarkus.io/guides/datasource)): Conecte-se ao banco de dados PostgreSQL via JDBC.

## Código Fornecido

### Hibernate ORM

Crie sua primeira entidade JPA

[Seção de guia relacionada...](https://quarkus.io/guides/hibernate-orm)

[Seção relacionada ao Hibernate com Panache...](https://quarkus.io/guides/hibernate-orm-panache)

### RESTEasy JAX-RS

Inicie facilmente seus serviços web RESTful

[Seção de guia relacioada...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

## Autores

- [Guilherme Rio Belo Correia Pereira](https://github.com/GuilhermeRioBel02)
- [Werbton Carvalho da Rocha Filho](https://github.com/wrbcrv)
