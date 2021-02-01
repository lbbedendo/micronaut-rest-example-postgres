package io.platosedu.api.dto;

import io.micronaut.core.annotation.Introspected;
import io.platosedu.enums.ModelScale;

import javax.validation.constraints.NotEmpty;

@Introspected
public class ModelSaveRequest {
    @NotEmpty
    private String name;
    private Integer modelYear;
    private ModelScale scale;
    private String colorRgba;
    private Long automakerId;
    private Long collectionId;
    private Long brandId;

    public ModelSaveRequest() {

    }

    public ModelSaveRequest(@NotEmpty String name,
                            Integer modelYear,
                            ModelScale scale,
                            String colorRgba,
                            Long automakerId,
                            Long collectionId,
                            Long brandId) {
        this.name = name;
        this.modelYear = modelYear;
        this.scale = scale;
        this.colorRgba = colorRgba;
        this.automakerId = automakerId;
        this.collectionId = collectionId;
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public ModelScale getScale() {
        return scale;
    }

    public void setScale(ModelScale scale) {
        this.scale = scale;
    }

    public String getColorRgba() {
        return colorRgba;
    }

    public void setColorRgba(String colorRgba) {
        this.colorRgba = colorRgba;
    }

    public Long getAutomakerId() {
        return automakerId;
    }

    public void setAutomakerId(Long automakerId) {
        this.automakerId = automakerId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
