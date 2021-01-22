package diecast.collector.api.repository;

import diecast.collector.api.domain.Brand;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
