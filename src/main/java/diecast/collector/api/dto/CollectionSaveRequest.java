package diecast.collector.api.dto;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class CollectionSaveRequest {
    @NotBlank
    private String name;
    private Integer year;

    public CollectionSaveRequest() {}

    public CollectionSaveRequest(@NotBlank String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
