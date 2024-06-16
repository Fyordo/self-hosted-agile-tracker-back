# Self-Hosted Agile-Tracker (Backend)

### About

This is a web application that integrates the functionalities of a Kanban board and time tracking in a single, convenient solution. It can be installed on your local server and customized to meet your specific needs.

### Project Setup

1. Clean all Maven files

```sh
./mvnw clean
```

2. Complie Maven

```sh
./mvnw compile
```

3. Run DB, configure `application-local.properties`

Example:
```
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.url=jdbc:postgresql://localhost:5434/shat_db_local
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
server.servlet.context-path=/api

server.port=8080

lk.path=http://localhost:4000

jwt.secret=dfghjkldfhfgjgkglowirghnsiognwouignqegengignsobisnpbs
```

4. Run app on local environment

#### Frontend: https://github.com/Fyordo/self-hosted-agile-tracker-app