package io.platosedu.migration;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.platosedu.config.DataSourceConfiguration;
import io.platosedu.config.MultitenancyConfiguration;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class FlywayRunner implements ApplicationEventListener<ServerStartupEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlywayRunner.class);
    private final MultitenancyConfiguration multitenancyConfiguration;
    private final DataSourceConfiguration dataSourceConfiguration;

    public FlywayRunner(MultitenancyConfiguration multitenancyConfiguration,
                        DataSourceConfiguration dataSourceConfiguration) {
        this.multitenancyConfiguration = multitenancyConfiguration;
        this.dataSourceConfiguration = dataSourceConfiguration;
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        var schemas = multitenancyConfiguration.getSchemas();

        for (String schema : schemas) {
            Flyway flyway = Flyway.configure()
                    .baselineOnMigrate(true)
                    .dataSource(dataSourceConfiguration.getUrl(),
                            dataSourceConfiguration.getUsername(),
                            dataSourceConfiguration.getPassword())
                    .schemas(schema)
                    .createSchemas(true)
                    .load();
            var result = flyway.migrate();
            LOGGER.info("{} migrations executed in schema {}", result.migrationsExecuted, schema);
        }

    }
}
