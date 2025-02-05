package com.lucianoortizsilva.migracao.datasource;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	
	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource springDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "databasea.datasource")
	public DataSource databaseADataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "databaseb.datasource")
	public DataSource databaseBDataSource() {
		return DataSourceBuilder.create().build();
	}
}