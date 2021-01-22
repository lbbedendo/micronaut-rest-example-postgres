package diecast.collector.api.api;

import diecast.collector.api.domain.Brand;
import diecast.collector.api.dto.BrandSaveRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

import javax.validation.Valid;
import java.util.List;

public interface BrandApi {
    @Get("/{id}")
    HttpResponse<Brand> getById(Integer id);

    @Get("/")
    List<Brand> getAll();

    @Post("/")
    HttpResponse<Brand> create(@Body @Valid BrandSaveRequest request);

    @Put("/{id}")
    HttpResponse<Brand> update(Integer id, @Body @Valid BrandSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Brand> delete(Integer id);
}
