package com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step01.chunk;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step01.EnderecoVO;

@Configuration
public class EscreveLogradouroTravessaJdbcWriterConfig {
	
	@Bean
	JdbcBatchItemWriter<EnderecoVO> escreveLogradouroTravessaJdbcWriter(@Qualifier("databaseBDataSource") final DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<EnderecoVO>()//
				.dataSource(dataSource)//
				.sql("INSERT INTO logradouro_travessa (cep, logradouro) VALUES (:cep, :logradouro)")//
				.beanMapped()//
				.build();//
	}
}