package net.j33r.flightcontrol.config;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;

/**
 * This class contains the persistence layer configuration. These configurations
 * are related to JPA and are database agnostic. All database configuration are
 * contained in the {@link DatabaseConfiguration} class.
 */
@Configuration
public class PersistenceConfiguration {

    /**
     * Configures the EntityManager factory.
     *
     * @param dataSource
     *            an instance of the {@link DataSource} class.
     * @return a {@link LocalContainerEntityManagerFactoryBean}
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("net.j33r.flightcontrol.domain");

        return entityManagerFactoryBean;
    }

    /**
     * Define the JPA dialect.
     *
     * @return the JPA dialect
     */
    @Bean
    public JpaDialect dialect() {
        return new HibernateJpaDialect();
    }

}
