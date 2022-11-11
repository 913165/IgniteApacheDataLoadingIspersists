package com.example.ignitedropcreatepopulate.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class IgniteDataSourceConfiguration {

    @Bean
    public DataSource igniteDataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.apache.ignite.IgniteJdbcThinDriver");
        hikariConfig.setJdbcUrl("jdbc:ignite:thin://20.228.216.42/");
        hikariConfig.setAutoCommit(false);
        //hikariConfig.setJdbcUrl("jdbc:ignite:thin://localhost/");
        DataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }
}
