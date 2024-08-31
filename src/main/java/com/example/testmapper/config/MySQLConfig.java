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
@Profile("mysql")
@EnableJpaRepositories(basePackages = "com.example.testmapper.mysql.repository",
        entityManagerFactoryRef = "mySqlEntityManagerFactoryBean", transactionManagerRef = "mySqlTransactionManager")
@EntityScan(basePackages = "com.example.testmapper.mysql.entity")
public class MySQLConfig {

    @Primary
    @Bean(name = "mySqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mySqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mySqlEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBean(@Qualifier("mySqlDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.testmapper.mysql.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);
        return em;
    }


    @Primary
    @Bean(name = "mySqlTransactionManager")
    public JpaTransactionManager mySqlTransactionManager(@Qualifier("mySqlEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
