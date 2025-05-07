### 📌 Technologies
![](https://github.com/lucianoortizsilva/migracao-dados-batch/blob/17dd7887d33cf6d897e7f72539ffa79310104c87/backend/src/main/resources/static/technologies.png?raw=true)

### 🎯 What is it ?
Is a web project development with spring batch, with objective to migrate data of side to other.


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
