package net.j33r.flightcontrol.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * This class contains all the database configuration for the Flight Control API
 * system.
 *
 * As this is an API server example, the database used is a embedded one. As
 * such, the configuration parameter values are included inside this class for
 * simplicity sake. On a production system, the parameter values should be
 * retrieved using environment variables or, if an application server is used, a
 * jndi datasource.
 */
@Configuration
@Profile("default")
public class DatabaseConfiguration {

    /**
     * Configure the {@link DataSource} used by the application
     *
     * @return an instance of {@link DataSource}
     */
    @Bean
    public DataSource dataSource() {
        final DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:hsqldb:mem:flightdb");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        dataSourceBuilder.driverClassName("org.hsqldb.jdbcDriver");
        return dataSourceBuilder.build();
    }

    /**
     * Creates a {@link DatabasePopulator} and executes the registered SQL
     * scripts used by the application.
     *
     * @param dataSource
     *            an instance of the {@link DataSource} class.
     * @return a {@link DatabasePopulator}
     */
    @Bean
    public DatabasePopulator databasePopulator(DataSource dataSource) {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema-default.sql"));
        populator.addScript(new ClassPathResource("data-default.sql"));

        DatabasePopulatorUtils.execute(populator, dataSource);

        return populator;
    }
}
