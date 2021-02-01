package io.platosedu.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.platosedu.domain.Automaker;
import io.platosedu.dto.filters.AutomakerFilters;
import org.jooq.DSLContext;

import java.util.List;

import static io.platosedu.jooq.tables.Automaker.AUTOMAKER;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public abstract class AutomakerRepository implements CrudRepository<Automaker, Long> {
    private final DSLContext dslContext;

    public AutomakerRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<Automaker> findAll(AutomakerFilters filters) {
        return dslContext
                .selectFrom(AUTOMAKER)
                .where(filters.toCondition())
                .fetchInto(Automaker.class);
    }
}
