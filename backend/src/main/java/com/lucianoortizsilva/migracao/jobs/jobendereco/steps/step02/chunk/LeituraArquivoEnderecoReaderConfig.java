package com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step02.chunk;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step02.EnderecoVO;

@Configuration
public class LeituraArquivoEnderecoReaderConfig {
	
	@Bean
	FlatFileItemReader<EnderecoVO> leituraArquivoEnderecoReader() {
		return new FlatFileItemReaderBuilder<EnderecoVO>()//
				.name("leituraArquivoEnderecoReader")//
				.resource(new FileSystemResource("files/arquivos-entrada/enderecos_1.csv"))//
				.delimited()//
				.names(new String[] { "cep", "logradouro", "complemento1", "complemento2", "complemento3", "complemento4" })//
				.fieldSetMapper(fieldSet -> new EnderecoVO(fieldSet.readString("cep"), fieldSet.readString("logradouro")))//
				.build();//
	}
}