package io.platosedu.controller;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.platosedu.api.ModelApi;
import io.platosedu.domain.Model;
import io.platosedu.api.dto.ModelSaveRequest;
import io.platosedu.service.ModelService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@Controller("/model")
public class ModelController implements ModelApi {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public HttpResponse<Model> findById(Long id) {
        return modelService.findById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public Page<Model> findAll(Pageable pageable) {
        return modelService.findAll(pageable);
    }

    @Override
    public HttpResponse<Model> create(@Valid @Body ModelSaveRequest request) {
        var model = modelService.create(request);
        return HttpResponse.created(model, location(model.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<Model> update(Long id, @Valid @Body ModelSaveRequest request) {
        return modelService.findById(id)
                .map(model -> HttpResponse.ok(modelService.update(model, request)))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    @Transactional
    public HttpResponse<Model> delete(Long id) {
        return modelService.findById(id)
                .map(model -> {
                    modelService.delete(model);
                    return HttpResponse.ok(model);
                })
                .orElseGet(HttpResponse::notFound);
    }

    private URI location(Long id) {
        return URI.create("/model/" + id);
    }
}
