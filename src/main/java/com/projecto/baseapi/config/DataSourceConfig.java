package com.projecto.baseapi.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("dev")
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:h2:file:~/test");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("sa");
        dataSourceBuilder.driverClassName("org.h2.Driver");
        return dataSourceBuilder.build();
    }

}
