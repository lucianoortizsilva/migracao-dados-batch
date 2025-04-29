package com.lucianoortizsilva.migracao.jobs.address.steps.step02;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Step02CreateAddressConfig {
	
	@Autowired private JobRepository jobRepository;
	@Autowired private PlatformTransactionManager transactionManager;
	
	@Bean
	Step step02CreateAddress(final ItemReader<AddressVO> fileAddressReader, final ClassifierCompositeItemWriter<AddressVO> classificationCompositeAddressWriter) {
		return new StepBuilder("step02CreateAddress", jobRepository)//
				.<AddressVO, AddressVO> chunk(1, transactionManager)//
				.reader(fileAddressReader)//
				.writer(classificationCompositeAddressWriter)//
				.build();//
	}
}