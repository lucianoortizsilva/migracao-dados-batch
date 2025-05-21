package com.lucianoortizsilva.migration.jobs.address.steps.step01;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.lucianoortizsilva.migration.jobs.address.steps.step01.tasklet.DeleteAddressTasklet;

@Configuration
public class Step01DeleteAddressConfig {
	
	@Autowired private JobRepository jobRepository;
	@Autowired private PlatformTransactionManager transactionManager;
	@Autowired private DeleteAddressTasklet tasklet;
	
	@Bean
	Step step01DeleteAddress() {
		return new StepBuilder("step01DeleteAddress", jobRepository)//
				.tasklet(tasklet, transactionManager)//
				.build();//
	}
}