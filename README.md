## 📌 Technologies
- Java 21
- Spring Boot
- Spring Batch
- PostgreSQL
- RabbitMQ
- Docker

<br>

## 🎯 What is it ?
Is a web project development with spring batch, with objective to migrate data of side to other.

<br>

## 🏗 Architecture

In application exists 2 jobs. 
An job called ``jobAddress``, and other job called ``jobFlight``.\
For to execute some job, had send message to RabbitMQ, with your name.

> http://localhost:15672 \
> user: guest\
> password: guest\
> queue: job_queue

Example:
```json
{
   "name" : "jobAddress"
}
```

or

```json
{
   "name" : "jobFlight"
}
```
<br>

![](https://github.com/lucianoortizsilva/migracao-dados-batch/blob/5cbce603b91176826b40a5f493e9b14e70fd8bb6/backend/src/main/resources/static/jobAddress.png?raw=true)    

<br>

![](https://github.com/lucianoortizsilva/migracao-dados-batch/blob/22de1b18648b011d5432544fa9c3ede6491d6bb1/backend/src/main/resources/static/jobFlight.png?raw=true)

## 🚀 How to execute the project ?

### In ``root`` folder execute

- sudo docker-compose down -v <br><br>
- sudo docker-compose up <br><br>

### In ``backend`` folder execute

- mvn clean package <br><br>
- mvn spring-boot:run <br><br>
