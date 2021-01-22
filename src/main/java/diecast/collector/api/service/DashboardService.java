package diecast.collector.api.service;

import diecast.collector.api.dto.response.ModelQuantityByAutomakerResponse;
import diecast.collector.api.dto.response.ModelQuantityByCollectionResponse;
import diecast.collector.api.dto.response.ModelsDashboardResponse;
import diecast.collector.api.repository.ModelRepository;

import javax.inject.Singleton;
import java.util.List;

@Singleton
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
