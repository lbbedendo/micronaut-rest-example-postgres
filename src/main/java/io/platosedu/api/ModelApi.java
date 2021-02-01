package io.platosedu.api;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.platosedu.domain.Model;
import io.platosedu.api.dto.ModelSaveRequest;

import javax.validation.Valid;

public interface ModelApi {
    @Get("/{id}")
    HttpResponse<Model> findById(Long id);

    @Get("/")
    Page<Model> findAll(Pageable pageable);

    @Post("/")
    HttpResponse<Model> create(@Valid @Body ModelSaveRequest request);

    @Put("/{id}")
    HttpResponse<Model> update(Long id, @Valid @Body ModelSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Model> delete(Long id);
}
