package io.platosedu.config.jooq;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.multitenancy.tenantresolver.TenantResolver;
import org.jooq.conf.MappedSchema;
import org.jooq.conf.RenderMapping;
import org.jooq.conf.Settings;

@Factory
public class JooqConfiguration {
    private final TenantResolver tenantResolver;

    public JooqConfiguration(TenantResolver tenantResolver) {
        this.tenantResolver = tenantResolver;
    }

    @Bean
    public Settings settings() {
        var tenantId = tenantResolver.resolveTenantIdentifier().toString();
        return new Settings()
                .withRenderMapping(new RenderMapping()
                        .withSchemata(
                                new MappedSchema().withInput("public")
                                        .withOutput(tenantId)));
    }
}
