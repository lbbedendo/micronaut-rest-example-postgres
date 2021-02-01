package io.platosedu.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import io.platosedu.domain.Brand;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface BrandRepository extends PageableRepository<Brand, Long> {
}
