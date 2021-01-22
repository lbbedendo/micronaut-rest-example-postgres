package diecast.collector.api.repository;

import diecast.collector.api.domain.Model;
import diecast.collector.api.dto.response.ModelQuantityByAutomakerResponse;
import diecast.collector.api.dto.response.ModelQuantityByCollectionResponse;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.Optional;

import static diecast.collector.api.tables.Automaker.AUTOMAKER;
import static diecast.collector.api.tables.Collection.COLLECTION;
import static diecast.collector.api.tables.Model.MODEL;

@Repository
public abstract class ModelRepository implements JpaRepository<Model, Integer> {
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
    public abstract Optional<Model> findById(@NonNull Integer id);

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
