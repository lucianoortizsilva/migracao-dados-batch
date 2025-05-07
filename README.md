## 📌 Technologies
![](https://github.com/lucianoortizsilva/migracao-dados-batch/blob/f75a1bca625ad95dfa4978fb108b6cf830a3fabe/backend/src/main/resources/static/technologies_.png?raw=true)

<br>

## 🎯 What is it ?
Is a web project development with spring batch, with objective to migrate data of side to other.

<br>

## 🚀 How to execute the project ?

### In root folder execute

> - sudo docker-compose down -v <br><br>
> - sudo docker-compose up <br><br>


### In ``backend`` folder execute

> - mvn clean package <br><br>
> - mvn spring-boot:run <br><br>


### Database

> **base:** database_a <br><br>

> **base:** database_b <br>
  - table: street_square
  - table: street_not_square 
<br><br>

> **base:** database_c <br><br>
