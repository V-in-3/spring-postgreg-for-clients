# REST API (Middle Java Developer, Some One Bank)

## Description

Customer service has endpoint which allow interaction with database Customer.<br />
Docker container jboss/keycloak (https://www.github.com/keycloak/keycloak-containers) is used for authorization. <br />
Service use PostgreSQL Database localy or H2 database in memory.<br />
It is possible to store data both locally in the Postgres database and in the Docker container.<br />
Service has swagger-ui definition endpoint.<br />
Service has actuator definition endpoint.<br />

##Task

You need to implement a REST API in Java that allows you to send an http POST request to it and receive a response.

## Requirements

1. Request and response in JSON format.
2. Request, response and date / time must be logged into a separate file.
3. The project must be built in war for installation on Tomcat.
4. If the request contains the "id" field with the value = 1, then the answer must be as in the example below, otherwise the answer must be NULL.
5. POST request format.
6. Implement AES-256 encryption and decryption of the incoming request and response. This part only needs to be secured.<br/>
      For example (part of the log):<br/>
      === encryption: sfdjnva9sfv87say9hdfow3<br/>
      === decryption: {"fio": "Test Testov"}<br/>

Example BODY request:
{"id": 1}

Example BODY response:
{"fio": "Test Testov"}

##Tools

It is recommended to use Maven, Spring (Rest Controller), Log4j. The rest is at the discretion of the developer. It is desirable to fulfill the maximum number of requirements :)

## Available endpoints

- `POST http://localhost:8080/api/customers` - interaction with DB Postgres
- `GET  http://localhost:8080/swagger-ui.html#/` - swagger
- `GET  http://localhost:8080/management/health` - for health checking
- `GET  http://localhost:8080/management/info` - for info getting
- `GET  http://localhost:8080/v2/api-docs` - api-docs info

