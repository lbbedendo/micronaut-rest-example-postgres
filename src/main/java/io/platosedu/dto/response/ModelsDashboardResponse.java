package io.platosedu.dto.response;

import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class ModelsDashboardResponse {
    private final List<ModelQuantityByAutomakerResponse> modelsByAutomaker;
    private final List<ModelQuantityByCollectionResponse> modelsByCollection;

    public ModelsDashboardResponse(List<ModelQuantityByAutomakerResponse> modelsByAutomaker,
                                   List<ModelQuantityByCollectionResponse> modelsByCollection) {
        this.modelsByAutomaker = modelsByAutomaker;
        this.modelsByCollection = modelsByCollection;
    }

    public List<ModelQuantityByAutomakerResponse> getModelsByAutomaker() {
        return modelsByAutomaker;
    }

    public List<ModelQuantityByCollectionResponse> getModelsByCollection() {
        return modelsByCollection;
    }
}
