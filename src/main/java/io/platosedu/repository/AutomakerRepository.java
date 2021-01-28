package io.platosedu.repository;

import io.platosedu.domain.Automaker;
import io.platosedu.dto.filters.AutomakerFilters;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.jooq.DSLContext;

import java.util.List;

import static io.platosedu.jooq.tables.Automaker.AUTOMAKER;

@Repository
public abstract class AutomakerRepository implements JpaRepository<Automaker, Integer> {
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
