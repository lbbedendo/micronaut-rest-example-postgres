package io.platosedu.controller;

import io.platosedu.api.AutomakerApi;
import io.platosedu.domain.Automaker;
import io.platosedu.dto.AutomakerSaveRequest;
import io.platosedu.dto.filters.AutomakerFilters;
import io.platosedu.service.AutomakerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller("/automaker")
public class AutomakerController implements AutomakerApi {
    private final AutomakerService automakerService;

    public AutomakerController(AutomakerService automakerService) {
        this.automakerService = automakerService;
    }

    @Override
    public HttpResponse<Automaker> getById(Integer id) {
        return automakerService.getById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public List<Automaker> getAll(AutomakerFilters filters) {
        return automakerService.findAll(filters);
    }

    @Override
    public HttpResponse<Automaker> create(@Body @Valid AutomakerSaveRequest request) {
        var automaker = automakerService.create(request);
        return HttpResponse.created(automaker, location(automaker.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<Automaker> update(Integer id, @Body @Valid AutomakerSaveRequest request) {
        return automakerService.getById(id)
                .map(automaker -> HttpResponse
                        .ok(automakerService.update(automaker, request))
                        .headers(headers -> headers.location(location(id))))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    @Transactional
    public HttpResponse<Automaker> delete(Integer id) {
        return automakerService.getById(id)
                .map(automaker -> {
                    automakerService.delete(automaker);
                    return HttpResponse.ok(automaker);
                })
                .orElseGet(HttpResponse::notFound);
    }

    private URI location(Integer id) {
        return URI.create("/automaker/" + id);
    }
}
