# Diecast Collector API

**diecast-collector-api** Is a REST API developed with **Micronaut Framework**. It's goal is to help me keep track of my miniature car collection (Hot Wheels, Maisto, California Collectibles, etc) and to provide a general use case for studying new technologies.
Also check out the [Web App](https://github.com/lbbedendo/diecast-collector-app) built with React.


## Technology Stack
- [Micronaut Framework](https://micronaut.io/) with Java
- PostgreSQL Database
- Testcontainers
- Gradle

## Running the application
### Prerequisites
- docker
- docker-compose
### Steps
1. Run `docker-compose up` to setup a postgresql 12.5 instance
2. The above command will create a new database (diecast_collector_dev) and execute an init script with the DDL necessary for jOOQ to work.
3. Run `./gradlew build` in the root directory of the repository. During compilation, jOOQ will connect to the database running in the container and generate all necessary classes for the project to compile succesfully.
4. If you see any error with the message `Failed to initialize pool: Container startup failed` during the test phase, you may need to run `docker run testcontainers/ryuk:0.3.0` and then `./gradlew build` agrain. This seems to be a testcontainers issue.
5. Finally, run `./gradlew run` to start the application.

## Reference Documentation

### jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

### testcontainers documentation

- [https://www.testcontainers.org/](https://www.testcontainers.org/)

### jooq documentation

- [Micronaut jOOQ fluent API for typesafe SQL query construction and execution documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jooq)

### flyway documentation

- [Micronaut Flyway Database Migration documentation](https://micronaut-projects.github.io/micronaut-flyway/latest/guide/index.html)

- [https://flywaydb.org/](https://flywaydb.org/)

### http-client documentation

- [Micronaut Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)