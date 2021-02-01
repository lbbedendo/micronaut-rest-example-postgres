package io.platosedu.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.platosedu.api.AutomakerApi;
import io.platosedu.domain.Automaker;
import io.platosedu.api.dto.AutomakerSaveRequest;
import io.platosedu.api.dto.filters.AutomakerFilters;
import io.platosedu.service.AutomakerService;

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
    public HttpResponse<Automaker> findById(Long id) {
        return automakerService.findById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public List<Automaker> findAll(AutomakerFilters filters) {
        return automakerService.findAll(filters);
    }

    @Override
    public HttpResponse<Automaker> create(@Body @Valid AutomakerSaveRequest request) {
        var automaker = automakerService.create(request);
        return HttpResponse.created(automaker, location(automaker.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<Automaker> update(Long id, @Body @Valid AutomakerSaveRequest request) {
        return automakerService.findById(id)
                .map(automaker -> HttpResponse
                        .ok(automakerService.update(automaker, request))
                        .headers(headers -> headers.location(location(id))))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    @Transactional
    public HttpResponse<Automaker> delete(Long id) {
        return automakerService.findById(id)
                .map(automaker -> {
                    automakerService.delete(automaker);
                    return HttpResponse.ok(automaker);
                })
                .orElseGet(HttpResponse::notFound);
    }

    private URI location(Long id) {
        return URI.create("/automaker/" + id);
    }
}
