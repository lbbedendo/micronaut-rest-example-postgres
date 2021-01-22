package diecast.collector.api.dto.response;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class ModelQuantityByCollectionResponse {
    private String collectionName;
    private Integer quantity;

    public ModelQuantityByCollectionResponse() {}

    public ModelQuantityByCollectionResponse(String collectionName, Integer quantity) {
        this.collectionName = collectionName;
        this.quantity = quantity;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
