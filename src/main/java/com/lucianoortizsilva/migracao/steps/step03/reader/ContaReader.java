package com.lucianoortizsilva.migracao.steps.step03.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import com.lucianoortizsilva.migracao.model.Conta;

@Configuration
public class ContaReader {
	
	@Bean
	FlatFileItemReader<Conta> buscarContasNoArquivoReader() {
		return new FlatFileItemReaderBuilder<Conta>()//
				.name("buscarContasNoArquivoReader")//
				.resource(new FileSystemResource("files/arquivos-entrada/contas.txt"))//
				.delimited()//
				.names(new String[] { "categoria", "saldo", "email" })//
				.targetType(Conta.class)//
				.build();//
	}
}