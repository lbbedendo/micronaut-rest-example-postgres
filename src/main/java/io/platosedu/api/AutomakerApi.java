package io.platosedu.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.platosedu.domain.Automaker;
import io.platosedu.api.dto.AutomakerSaveRequest;
import io.platosedu.api.dto.filters.AutomakerFilters;

import javax.validation.Valid;
import java.util.List;

public interface AutomakerApi {
    @Get("/{id}")
    HttpResponse<Automaker> findById(Long id);

    @Get("/{?filters*}")
    List<Automaker> findAll(AutomakerFilters filters);

    @Post("/")
    HttpResponse<Automaker> create(@Body @Valid AutomakerSaveRequest request);

    @Put("/{id}")
    HttpResponse<Automaker> update(Long id, @Body @Valid AutomakerSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Automaker> delete(Long id);
}
