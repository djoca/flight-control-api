package net.j33r.flightcontrol.config;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;

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
// @EnableJpaRepositories("net.j33r.flightcontrol.domain")
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        final DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:hsqldb:mem:flightdb");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        dataSourceBuilder.driverClassName("org.hsqldb.jdbcDriver");
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("net.j33r.flightcontrol.domain");

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaDialect dialect() {
        return new HibernateJpaDialect();
    }
}
