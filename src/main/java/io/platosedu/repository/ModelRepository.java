package io.platosedu.repository;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import io.platosedu.domain.Model;
import io.platosedu.dto.response.ModelQuantityByAutomakerResponse;
import io.platosedu.dto.response.ModelQuantityByCollectionResponse;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.Optional;

import static io.platosedu.jooq.tables.Automaker.AUTOMAKER;
import static io.platosedu.jooq.tables.Collection.COLLECTION;
import static io.platosedu.jooq.tables.Model.MODEL;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public abstract class ModelRepository implements PageableRepository<Model, Long> {
    private final DSLContext dslContext;

    public ModelRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @NonNull
    @Override
    @Join(value = "automaker", type = Join.Type.LEFT_FETCH)
    @Join(value = "collection", type = Join.Type.LEFT_FETCH)
    @Join(value = "brand", type = Join.Type.LEFT_FETCH)
    public abstract List<Model> findAll();

    @NonNull
    @Override
    @Join(value = "automaker", type = Join.Type.LEFT_FETCH)
    @Join(value = "collection", type = Join.Type.LEFT_FETCH)
    @Join(value = "brand", type = Join.Type.LEFT_FETCH)
    public abstract Optional<Model> findById(@NonNull Long id);

    public List<ModelQuantityByAutomakerResponse> loadModelQuantityByAutomakers() {
        return dslContext
                .select(
                        AUTOMAKER.NAME.as("automakerName"),
                        DSL.count().as("quantity"))
                .from(MODEL)
                .leftJoin(AUTOMAKER).on(MODEL.AUTOMAKER_ID.eq(AUTOMAKER.ID))
                .groupBy(AUTOMAKER.NAME)
                .fetchInto(ModelQuantityByAutomakerResponse.class);
    }

    public List<ModelQuantityByCollectionResponse> loadModelQuantityByCollection() {
        return dslContext
                .select(
                        COLLECTION.NAME.as("collectionName"),
                        DSL.count().as("quantity"))
                .from(MODEL)
                .leftJoin(COLLECTION).on(MODEL.COLLECTION_ID.eq(COLLECTION.ID))
                .groupBy(COLLECTION.NAME)
                .fetchInto(ModelQuantityByCollectionResponse.class);
    }
}
