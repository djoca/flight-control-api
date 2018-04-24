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
 * This class contains the database configuration used in tests.
 */
@Configuration
@Profile("test")
public class DatabaseTestConfiguration {

    /**
     * Configure the {@link DataSource} used by the integration tests
     *
     * @return an instance of {@link DataSource}
     */

    @Bean
    public DataSource dataSource() {
        final DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:hsqldb:mem:flightdb-test");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        dataSourceBuilder.driverClassName("org.hsqldb.jdbcDriver");
        return dataSourceBuilder.build();
    }

    /**
     * Creates a {@link DatabasePopulator} and executes the registered SQL
     * scripts used by the integration tests.
     *
     * @param dataSource
     *            an instance of the {@link DataSource} class.
     * @return a {@link DatabasePopulator}
     */
    @Bean
    public DatabasePopulator databasePopulator(DataSource dataSource) {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema-test.sql"));
        populator.addScript(new ClassPathResource("data-test.sql"));

        DatabasePopulatorUtils.execute(populator, dataSource);

        return populator;
    }

}
