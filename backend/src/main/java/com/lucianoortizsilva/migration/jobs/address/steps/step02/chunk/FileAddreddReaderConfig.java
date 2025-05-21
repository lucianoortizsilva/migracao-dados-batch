package com.lucianoortizsilva.migration.jobs.address.steps.step02.chunk;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import com.lucianoortizsilva.migration.jobs.address.steps.step02.AddressVO;

@Configuration
public class FileAddreddReaderConfig {
	
	@Bean
	FlatFileItemReader<AddressVO> fileAddressReader() {
		return new FlatFileItemReaderBuilder<AddressVO>()//
				.name("fileAddressReader")//
				.resource(new FileSystemResource("files/address.csv"))//
				.delimited()//
				.names(new String[] { "cep", "logradouro", "complemento1", "complemento2", "complemento3", "complemento4" })//
				.fieldSetMapper(fieldSet -> new AddressVO(fieldSet.readString("cep"), fieldSet.readString("logradouro")))//
				.build();//
	}
}