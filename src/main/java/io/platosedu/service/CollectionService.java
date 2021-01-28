package io.platosedu.service;

import io.platosedu.domain.Collection;
import io.platosedu.dto.CollectionSaveRequest;
import io.platosedu.repository.CollectionRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class CollectionService {
    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Optional<Collection> getById(Integer id) {
        return collectionRepository.findById(id);
    }

    public List<Collection> getAll() {
        return collectionRepository.findAll();
    }

    public Collection create(CollectionSaveRequest request) {
        var collection = new Collection(request.getName(), request.getYear());
        return collectionRepository.save(collection);
    }

    public Collection update(Collection collection, CollectionSaveRequest request) {
        collection.setName(request.getName());
        collection.setYear(request.getYear());
        return collectionRepository.save(collection);
    }

    public void delete(Collection collection) {
        collectionRepository.delete(collection);
    }
}
