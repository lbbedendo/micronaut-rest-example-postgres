package diecast.collector.api.repository;

import diecast.collector.api.domain.Collection;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {
}
