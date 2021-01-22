package diecast.collector.api.controller;

import diecast.collector.api.api.ModelApi;
import diecast.collector.api.domain.Model;
import diecast.collector.api.dto.ModelSaveRequest;
import diecast.collector.api.service.ModelService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller("/model")
public class ModelController implements ModelApi {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public HttpResponse<Model> getById(Integer id) {
        return modelService.getById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public List<Model> getAll() {
        return modelService.getAll();
    }

    @Override
    public HttpResponse<Model> create(@Valid @Body ModelSaveRequest request) {
        var model = modelService.create(request);
        return HttpResponse.created(model, location(model.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<Model> update(Integer id, @Valid @Body ModelSaveRequest request) {
        return modelService.getById(id)
                .map(model -> HttpResponse.ok(modelService.update(model, request)))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    @Transactional
    public HttpResponse<Model> delete(Integer id) {
        return modelService.getById(id)
                .map(model -> {
                    modelService.delete(model);
                    return HttpResponse.ok(model);
                })
                .orElseGet(HttpResponse::notFound);
    }

    private URI location(Integer id) {
        return URI.create("/model/" + id);
    }
}
