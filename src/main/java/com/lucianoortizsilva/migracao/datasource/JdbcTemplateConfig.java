package com.lucianoortizsilva.migracao.datasource;

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
	
	@Bean(name = "JdbcTemplateADataSource")
	JdbcTemplate databaseADataSource(@Qualifier("databaseADataSource") final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "JdbcTemplateBDataSource")
	JdbcTemplate secondaryJdbcTemplate(@Qualifier("databaseBDataSource") final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}