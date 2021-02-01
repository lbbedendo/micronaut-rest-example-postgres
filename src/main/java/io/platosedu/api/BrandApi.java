package io.platosedu.api;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.platosedu.domain.Brand;
import io.platosedu.api.dto.BrandSaveRequest;

import javax.validation.Valid;

public interface BrandApi {
    @Get("/{id}")
    HttpResponse<Brand> findById(Long id);

    @Get("/")
    Page<Brand> findAll(Pageable pageable);

    @Post("/")
    HttpResponse<Brand> create(@Body @Valid BrandSaveRequest request);

    @Put("/{id}")
    HttpResponse<Brand> update(Long id, @Body @Valid BrandSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Brand> delete(Long id);
}
