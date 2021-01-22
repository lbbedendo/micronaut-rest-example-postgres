package diecast.collector.api.api;

import diecast.collector.api.domain.Model;
import diecast.collector.api.dto.ModelSaveRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

import javax.validation.Valid;
import java.util.List;

public interface ModelApi {
    @Get("/{id}")
    HttpResponse<Model> getById(Integer id);

    @Get("/")
    List<Model> getAll();

    @Post("/")
    HttpResponse<Model> create(@Valid @Body ModelSaveRequest request);

    @Put("/{id}")
    HttpResponse<Model> update(Integer id, @Valid @Body ModelSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Model> delete(Integer id);
}
