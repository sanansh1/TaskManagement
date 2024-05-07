package com.api.mvctaskmgmt.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
/*
Configuration file for database
 */
@Configuration
public class JpaConfig {
    @Bean
    public DataSource dataSource() {
        // Use an embedded database like H2
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                // .addScript("schema.sql") // Optional: Initialize database schema
                .build();
    }
}
