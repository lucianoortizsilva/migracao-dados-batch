package com.lucianoortizsilva.migracao.jobs.address.steps.step02.chunk;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lucianoortizsilva.migracao.jobs.address.steps.step02.AddressVO;

@Configuration
public class StreetNotSquareJdbcWriterConfig {
	
	@Bean
	JdbcBatchItemWriter<AddressVO> streetNotSquareJdbcWriter(@Qualifier("databaseBDataSource") final DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<AddressVO>()//
				.dataSource(dataSource)//
				.sql("INSERT INTO street_not_square (cep, logradouro) VALUES (:cep, :logradouro)")//
				.beanMapped()//
				.build();//
	}
}