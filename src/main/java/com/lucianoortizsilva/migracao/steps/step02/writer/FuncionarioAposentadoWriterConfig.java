package com.lucianoortizsilva.migracao.steps.step02.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import com.lucianoortizsilva.migracao.model.Funcionario;

@Configuration
public class FuncionarioAposentadoWriterConfig {
	
	@Bean
	FlatFileItemWriter<Funcionario> escreverFuncionarioAposentadoWriter() {
		return new FlatFileItemWriterBuilder<Funcionario>()//
				.name("escreverFuncionarioAposentadoWriter")//
				.resource(new FileSystemResource("files/arquivos-saida/funcionario_aposentados.csv"))//
				.delimited()//
				.names("nome")//
				.build();//
	}
}