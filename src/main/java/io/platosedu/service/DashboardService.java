package io.platosedu.service;

import io.platosedu.dto.response.ModelQuantityByAutomakerResponse;
import io.platosedu.dto.response.ModelQuantityByCollectionResponse;
import io.platosedu.dto.response.ModelsDashboardResponse;
import io.platosedu.repository.ModelRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
@Transactional
public class DashboardService {
    private final ModelRepository modelRepository;

    public DashboardService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public ModelsDashboardResponse getModelsDashboardResponse() {
        return new ModelsDashboardResponse(
                loadModelQuantityByAutomakers(),
                loadModelQuantityByCollection()
        );
    }

    private List<ModelQuantityByAutomakerResponse> loadModelQuantityByAutomakers() {
        return modelRepository.loadModelQuantityByAutomakers();
    }

    private List<ModelQuantityByCollectionResponse> loadModelQuantityByCollection() {
        return modelRepository.loadModelQuantityByCollection();
    }
}
