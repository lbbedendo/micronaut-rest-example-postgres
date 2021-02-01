package io.platosedu.controller;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.platosedu.api.CollectionApi;
import io.platosedu.domain.Collection;
import io.platosedu.api.dto.CollectionSaveRequest;
import io.platosedu.service.CollectionService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@Controller("/collection")
public class CollectionController implements CollectionApi {
    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Override
    public HttpResponse<Collection> findById(Long id) {
        return collectionService.findById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public Page<Collection> findAll(Pageable pageable) {
        return collectionService.findAll(pageable);
    }

    @Override
    public HttpResponse<Collection> create(@Body @Valid CollectionSaveRequest request) {
        var collection = collectionService.create(request);
        return HttpResponse.created(collection, location(collection.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<Collection> update(Long id, @Body @Valid CollectionSaveRequest request) {
        return collectionService.findById(id)
                .map(collection -> HttpResponse
                        .ok(collectionService.update(collection, request))
                        .headers(headers -> headers.location(location(collection.getId()))))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    @Transactional
    public HttpResponse<Collection> delete(Long id) {
        return collectionService.findById(id)
                .map(collection -> {
                    collectionService.delete(collection);
                    return HttpResponse.ok(collection);
                })
                .orElseGet(HttpResponse::notFound);
    }

    private URI location(Long id) {
        return URI.create("/collection/" + id);
    }
}
