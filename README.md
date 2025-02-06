### Tecnologias
- Java 21
- Spring Boot 3.4.2
- Spring Batch 5.2.1
- Docker
- PostgreSQL

<hr>

### O que é ?
É uma POC de um projeto web desenvolvido com spring batch, com o objetivo de migrar alguns dados de um lado para outro.

![](https://github.com/lucianoortizsilva/migracao-dados-batch/blob/5f1f031ea8b8b98d52873896f1e6ab226807cf99/src/main/resources/static/800px-arquitetura.png?raw=true)

<hr>

### Como rodar ?

> 1º Rodar docker-compose, para preparar uma base de dados e criar servidores PostgreSQL.
- Na raiz do repositório, execute `docker-compose up`

> 2º Build:
- Na raiz do repositorio: **`mvn clean package`**

> 3º Executar JOB:
- Na raiz do repositorio: **`mvn spring-boot:run`**
