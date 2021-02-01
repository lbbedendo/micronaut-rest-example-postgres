package io.platosedu.service;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Automaker;
import io.platosedu.domain.Brand;
import io.platosedu.domain.Collection;
import io.platosedu.domain.Model;
import io.platosedu.api.dto.ModelSaveRequest;
import io.platosedu.repository.ModelRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Transactional
public class ModelService {
    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public Optional<Model> findById(Long id) {
        return modelRepository.findById(id);
    }

    public Page<Model> findAll(Pageable pageable) {
        return modelRepository.findAll(pageable);
    }

    @Transactional
    public Model create(ModelSaveRequest request) {
        var model = new Model(
                request.getName(),
                request.getModelYear(),
                request.getScale(),
                request.getColorRgba(),
                new Automaker(request.getAutomakerId()),
                new Collection(request.getCollectionId()),
                new Brand(request.getBrandId()));
        return modelRepository.save(model);
    }

    @Transactional
    public Model update(Model model, ModelSaveRequest request) {
        model.setName(request.getName());
        model.setModelYear(request.getModelYear());
        model.setScale(request.getScale());
        model.setColorRgba(request.getColorRgba());
        model.setAutomaker(request.getAutomakerId() != null
                ? new Automaker(request.getAutomakerId())
                : null);
        model.setCollection(request.getCollectionId() != null
                ? new Collection(request.getCollectionId())
                : null);
        model.setBrand(request.getBrandId() != null
                ? new Brand(request.getBrandId())
                : null);
        return modelRepository.update(model);
    }

    @Transactional
    public void delete(Model model) {
        modelRepository.delete(model);
    }
}
