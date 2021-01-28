## Stack
- [Micronaut Framework](https://micronaut.io/)
- PostgreSQL
- Testcontainers
- Gradle

## Rodando a aplicação
### Pré-requisitos
- docker
- docker-compose
### Passos
1. Execute `docker-compose up` para iniciar uma instância do postgresql 12.5
2. O comando acima irá criar um banco de dados com o nome "diecast_collector_dev".
3. Execute `./gradlew build` no diretório raiz do projeto. O script de build está configurado para executar todas as migrações do Flyway **antes** do plugin do jOOQ gerar as classes java baseadas no schema do banco. Finalizado o processo do Flyway, o plugin do jOOQ irá conectar no banco de dados e gerar as classes necessárias para o projeto compilar com sucesso.
4. Se você encontrar algum erro com a mensagem `Failed to initialize pool: Container startup failed` durante a execução dos testes da aplicação, tente executar o seguinte comando: `docker run testcontainers/ryuk:0.3.0` e depois `./gradlew build` novamente. Esse erro parece ser proveniente da biblioteca Testcontainers.
5. Execute `./gradlew run` para iniciar a aplicação.
6. Teste a API executando um POST para `http://localhost:8080/automaker` com o seguinte payload:
```json
{
    "name": "Porsche",
    "country": "Germany" 
}
```
7. Verifique se o registro foi criado executando um GET para `http://localhost:8080/automaker`

## REST clients para testar a API
- [Insomnia](https://insomnia.rest/download/)
- [Postman](https://www.postman.com/)

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