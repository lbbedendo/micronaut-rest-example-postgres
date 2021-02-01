package io.platosedu.service;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Collection;
import io.platosedu.api.dto.CollectionSaveRequest;
import io.platosedu.repository.CollectionRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Transactional
public class CollectionService {
    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Optional<Collection> findById(Long id) {
        return collectionRepository.findById(id);
    }

    public Page<Collection> findAll(Pageable pageable) {
        return collectionRepository.findAll(pageable);
    }

    public Collection create(CollectionSaveRequest request) {
        var collection = new Collection();
        collection.setName(request.getName());
        collection.setYear(request.getYear());
        return collectionRepository.save(collection);
    }

    public Collection update(Collection collection, CollectionSaveRequest request) {
        collection.setName(request.getName());
        collection.setYear(request.getYear());
        return collectionRepository.update(collection);
    }

    public void delete(Collection collection) {
        collectionRepository.delete(collection);
    }
}
