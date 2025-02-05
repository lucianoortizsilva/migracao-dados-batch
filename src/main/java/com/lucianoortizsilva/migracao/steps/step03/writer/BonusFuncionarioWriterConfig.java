package com.lucianoortizsilva.migracao.steps.step03.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import com.lucianoortizsilva.migracao.model.BonusFuncionario;

@Configuration
public class BonusFuncionarioWriterConfig {
	
	@Bean
	FlatFileItemWriter<BonusFuncionario> escreverNoArquivoBonusFuncionarioWriter() {
		return new FlatFileItemWriterBuilder<BonusFuncionario>()//
				.name("escreverNoArquivoBonusFuncionarioWriter")//
				.resource(new FileSystemResource("files/arquivos-saida/bonus_funcionario.csv"))//
				.delimited()//
				.names("nome", "email", "valor")//
				.build();//
	}
}