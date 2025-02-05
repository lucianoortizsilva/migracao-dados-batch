package com.lucianoortizsilva.migracao.steps.step02.writer;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lucianoortizsilva.migracao.model.Funcionario;

@Configuration
public class FuncionarioNaoAposentadoWriterConfig {
	
	@Bean
	JdbcBatchItemWriter<Funcionario> escreverFuncionarioNaoAposentadoWriter(@Qualifier("databaseBDataSource") final DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Funcionario>()//
				.dataSource(dataSource)//
				.sql("INSERT INTO funcionario_nao_aposentado (nome, idade, email, salario) VALUES (:nome, :idade, :email, :salario)")//
				.beanMapped()//
				.build();//
	}
}