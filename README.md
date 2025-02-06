### Tecnologias
- Java 21
- Spring Boot 3.4.2
- Spring Batch 5.2.1
- Docker
- PostgreSQL

<hr>

### O que é ?
É uma POC de um projeto web desenvolvido com spring batch, com o objetivo de para migrar dados.

<hr>

### Como rodar ?

> 1º Rodar docker-compose, para preparar uma base de dados e criar servidores PostgreSQL.
- Na raiz do repositório, execute `docker-compose up`

> 2º Build:
- Na raiz do repositorio: **`mvn clean package`**

> 3º Executar JOB:
- Na raiz do repositorio: **`mvn spring-boot:run`**

<hr>