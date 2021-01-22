package diecast.collector.api.dto.response;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class ModelQuantityByAutomakerResponse {
    private String automakerName;
    private Integer quantity;

    public ModelQuantityByAutomakerResponse() {}

    public ModelQuantityByAutomakerResponse(String automakerName, Integer quantity) {
        this.automakerName = automakerName;
        this.quantity = quantity;
    }

    public String getAutomakerName() {
        return automakerName;
    }

    public void setAutomakerName(String automakerName) {
        this.automakerName = automakerName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
