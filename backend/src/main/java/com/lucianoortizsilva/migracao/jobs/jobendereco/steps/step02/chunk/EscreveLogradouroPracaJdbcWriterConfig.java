package com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step02.chunk;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step02.EnderecoVO;

@Configuration
public class EscreveLogradouroPracaJdbcWriterConfig {
	
	@Bean
	JdbcBatchItemWriter<EnderecoVO> escreveLogradouroPracaJdbcWriter(@Qualifier("databaseBDataSource") final DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<EnderecoVO>()//
				.dataSource(dataSource)//
				.sql("INSERT INTO logradouro_praca (cep, logradouro) VALUES (:cep, :logradouro)")//
				.beanMapped()//
				.build();//
	}
}