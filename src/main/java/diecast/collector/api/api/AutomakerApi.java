package diecast.collector.api.api;

import diecast.collector.api.domain.Automaker;
import diecast.collector.api.dto.AutomakerSaveRequest;
import diecast.collector.api.dto.filters.AutomakerFilters;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

import javax.validation.Valid;
import java.util.List;

public interface AutomakerApi {
    @Get("/{id}")
    HttpResponse<Automaker> getById(Integer id);

    @Get("/{?filters*}")
    List<Automaker> getAll(AutomakerFilters filters);

    @Post("/")
    HttpResponse<Automaker> create(@Body @Valid AutomakerSaveRequest request);

    @Put("/{id}")
    HttpResponse<Automaker> update(Integer id, @Body @Valid AutomakerSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Automaker> delete(Integer id);
}
