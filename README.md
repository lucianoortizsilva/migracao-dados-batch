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

O **`Step01`** é responsável por deletar todos os dados da tabela funcionario_nao_aposentado (database_b).

O **`Step02`** é responsável por:
  - 1º Reader = Ler dados da tabela funcionario (database_a);
  - 2º Writer = Temos um "ClassifierCompositeItemWriter", reponsável por decidir onde irá escrever:
    - Pode escrever alguns dados de leitura em: files/arquivos-saida/funcionario_aposentados.csv\
      Ou
    - Pode escrever alguns dados da leitura em: tabela funcionario_nao_aposentado (database_b)
      
O **`Step03`** é responsável por:
  - 1º Reader = Ler dados do arquivo: files/arquivos-entrada/nivel_funcionario.txt
  - 2º Processor = Implementa regra de negocio para calcular bonus dos funcionários.
  - 3º Writer = Escrever dados no arquivo: files/arquivos-saida/bonus_funcionario.csv

<hr>

### Como rodar ?

> 1º Rodar docker-compose, para preparar uma base de dados e criar servidores PostgreSQL.
- Na raiz do repositório, execute `docker-compose up`

> 2º Build:
- Na raiz do repositorio: **`mvn clean package`**

> 3º Executar JOB:
- Na raiz do repositorio: **`mvn spring-boot:run`**
