package com.pl.github.api.gitapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
public class GitApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        // Set other necessary properties, such as package names for entity scanning
//        factoryBean.afterPropertiesSet();
//        return factoryBean.getObject();
//    }
//
//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
////        dataSourceBuilder.driverClassName("org.h2.Driver");
//        dataSourceBuilder.url("jdbc:postgresql://postgres:5432/github");
//        dataSourceBuilder.username("github");
//        dataSourceBuilder.password("github");
//        return dataSourceBuilder.build();
//    }
}
