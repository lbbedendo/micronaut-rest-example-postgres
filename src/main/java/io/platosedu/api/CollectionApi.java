package io.platosedu.api;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.platosedu.domain.Collection;
import io.platosedu.api.dto.CollectionSaveRequest;

import javax.validation.Valid;

public interface CollectionApi {
    @Get("/{id}")
    HttpResponse<Collection> findById(Long id);

    @Get("/")
    Page<Collection> findAll(Pageable pageable);

    @Post("/")
    HttpResponse<Collection> create(@Body @Valid CollectionSaveRequest request);

    @Put("/{id}")
    HttpResponse<Collection> update(Long id, @Body @Valid CollectionSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Collection> delete(Long id);
}
