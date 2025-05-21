package com.lucianoortizsilva.migracao.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcTemplateConfig {
	
	@Bean(name = "JdbcTemplateSpringDataSource")
	JdbcTemplate primaryJdbcTemplate(@Qualifier("springDataSource") final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "JdbcTemplateDatalakeSource")
	JdbcTemplate datalakeDataSource(@Qualifier("datalakeDataSource") final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "JdbcTemplateDatawarehouseSource")
	JdbcTemplate secondaryJdbcTemplate(@Qualifier("datawarehouseDataSource") final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}