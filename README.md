# REST API (Middle Java Developer, Concord Bank)

## Description

Customer service has endpoint which allow interaction with database Customer.<br />
Service use PostgreSQL Database localy or H2 database in memory.<br />
It is possible to store data both locally in the Postgres database and in the Docker container.<br />
Service has swagger-ui definition endpoint.<br />
Service has actuator definition endpoint.<br />

## Available endpoints

- `POST http://localhost:8080/api/customers` - interaction with DB Postgres
- `GET  http://localhost:8080/swagger-ui.html#/` - swagger
- `GET  http://localhost:8080/management/health` - for health checking
- `GET  http://localhost:8080/management/info` - for info getting
- `GET  http://localhost:8080/v2/api-docs` - api-docs info

