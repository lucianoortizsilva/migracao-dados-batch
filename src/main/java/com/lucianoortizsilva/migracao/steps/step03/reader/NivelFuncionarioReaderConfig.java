package com.lucianoortizsilva.migracao.steps.step03.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import com.lucianoortizsilva.migracao.model.NivelFuncionario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class NivelFuncionarioReaderConfig implements ItemReader<NivelFuncionario> {
	
	@Bean
	FlatFileItemReader<NivelFuncionario> buscarNivelFuncionariosNoArquivoReader() {
		return new FlatFileItemReaderBuilder<NivelFuncionario>()//
				.name("buscarNivelFuncionariosNoArquivoReader")//
				.resource(new FileSystemResource("files/arquivos-entrada/nivel_funcionario.txt"))//
				.delimited()//
				.names(new String[] { "categoria", "email" })//
				.targetType(NivelFuncionario.class)//
				.build();//
	}
	
	@Override
	public NivelFuncionario read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return null;
	}
}