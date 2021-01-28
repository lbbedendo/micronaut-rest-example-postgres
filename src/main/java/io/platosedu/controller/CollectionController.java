package io.platosedu.controller;

import io.platosedu.api.CollectionApi;
import io.platosedu.domain.Collection;
import io.platosedu.dto.CollectionSaveRequest;
import io.platosedu.service.CollectionService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller("/collection")
public class CollectionController implements CollectionApi {
    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Override
    public HttpResponse<Collection> getById(Integer id) {
        return collectionService.getById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public List<Collection> getAll() {
        return collectionService.getAll();
    }

    @Override
    public HttpResponse<Collection> create(@Body @Valid CollectionSaveRequest request) {
        var collection = collectionService.create(request);
        return HttpResponse.created(collection, location(collection.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<Collection> update(Integer id, @Body @Valid CollectionSaveRequest request) {
        return collectionService.getById(id)
                .map(collection -> HttpResponse
                        .ok(collectionService.update(collection, request))
                        .headers(headers -> headers.location(location(collection.getId()))))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    @Transactional
    public HttpResponse<Collection> delete(Integer id) {
        return collectionService.getById(id)
                .map(collection -> {
                    collectionService.delete(collection);
                    return HttpResponse.ok(collection);
                })
                .orElseGet(HttpResponse::notFound);
    }

    private URI location(Integer id) {
        return URI.create("/collection/" + id);
    }
}
