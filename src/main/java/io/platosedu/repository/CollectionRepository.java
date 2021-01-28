package io.platosedu.repository;

import io.platosedu.domain.Collection;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {
}
