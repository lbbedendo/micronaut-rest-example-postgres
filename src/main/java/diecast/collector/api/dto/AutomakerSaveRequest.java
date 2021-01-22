package diecast.collector.api.dto;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class AutomakerSaveRequest {
    @NotBlank
    private String name;
    private String country;

    public AutomakerSaveRequest() {}

    public AutomakerSaveRequest(@NotBlank String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
