### 📌 Tecnologias
- Java 21
- Spring Boot 3.4.2
- Spring Batch 5.2.1
- Maven
- Docker
- PostgreSQL


### 🎯 O que é ?
É uma POC de um projeto web desenvolvido com spring batch, com o objetivo de migrar alguns dados de um lado para outro.


### 📉 Na raiz do projeto execute

> - sudo docker-compose down -v <br><br>
> - sudo docker-compose up <br><br>


### 📉 Na pasta ```backend``` execute

> - mvn clean package <br><br>
> - mvn spring-boot:run <br><br>


### 🎲 Banco de dados

> **banco:** database_a <br><br>

> **banco:** database_b <br>
  - table: logradouro_travessa
  - table: logradouro_praca 
<br><br>

> **banco:** database_c <br><br>