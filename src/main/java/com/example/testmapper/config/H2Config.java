package com.example.testmapper.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@Profile("h2")
@EnableJpaRepositories(basePackages = "com.example.testmapper.h2.repository", entityManagerFactoryRef = "h2EntityManagerFactoryBean", transactionManagerRef = "h2TransactionManager")
@EntityScan(basePackages = "com.example.testmapper.h2.entity")
public class H2Config {

    @Bean(name = "h2DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.h2")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "h2EntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactoryBean(@Qualifier("h2DataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.testmapper.h2.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "h2TransactionManager")
    @Primary
    public JpaTransactionManager h2TransactionManager(@Qualifier("h2EntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean entityManagerFactory) {

        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
