package diecast.collector.api.dto;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class BrandSaveRequest {
    @NotBlank
    private String name;

    public BrandSaveRequest() {}

    public BrandSaveRequest(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
