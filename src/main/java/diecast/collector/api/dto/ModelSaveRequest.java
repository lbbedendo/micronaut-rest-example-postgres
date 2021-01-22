package diecast.collector.api.dto;

import diecast.collector.api.domain.Automaker;
import diecast.collector.api.domain.Brand;
import diecast.collector.api.domain.Collection;
import diecast.collector.api.enums.ModelScale;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotEmpty;

@Introspected
public class ModelSaveRequest {
    @NotEmpty
    private String name;
    private Integer modelYear;
    private ModelScale scale;
    private String colorRgba;
    private Integer automakerId;
    private Integer collectionId;
    private Integer brandId;

    public ModelSaveRequest() {}

    public ModelSaveRequest(@NotEmpty String name,
                            Integer modelYear,
                            ModelScale scale,
                            String colorRgba,
                            Integer automakerId,
                            Integer collectionId,
                            Integer brandId) {
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

    public Integer getAutomakerId() {
        return automakerId;
    }

    public void setAutomakerId(Integer automakerId) {
        this.automakerId = automakerId;
    }

    public Automaker getAutomaker() {
        return automakerId != null ? new Automaker(automakerId) : null;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Collection getCollection() {
        return collectionId != null ? new Collection(collectionId) : null;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Brand getBrand() {
        return brandId != null ? new Brand(brandId) : null;
    }
}
