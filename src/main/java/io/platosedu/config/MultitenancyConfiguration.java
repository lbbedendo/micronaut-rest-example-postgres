package io.platosedu.config;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("multitenancy")
public class MultitenancyConfiguration {

    private String[] schemas;

    public String[] getSchemas() {
        return schemas.clone();
    }

    public void setSchemas(String[] schemas) {
        this.schemas = schemas.clone();
    }
}
