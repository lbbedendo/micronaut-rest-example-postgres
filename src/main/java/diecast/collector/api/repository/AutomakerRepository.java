package diecast.collector.api.repository;

import diecast.collector.api.domain.Automaker;
import diecast.collector.api.dto.filters.AutomakerFilters;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.jooq.DSLContext;

import java.util.List;

import static diecast.collector.api.tables.Automaker.AUTOMAKER;

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
