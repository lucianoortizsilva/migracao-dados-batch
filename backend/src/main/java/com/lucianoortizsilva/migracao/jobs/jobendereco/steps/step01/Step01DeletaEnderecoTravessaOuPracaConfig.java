package com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step01;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step01.tasklet.DeletaEnderecoTravessaOuPracaTasklet;

@Configuration
public class Step01DeletaEnderecoTravessaOuPracaConfig {
	
	@Autowired private JobRepository jobRepository;
	@Autowired private PlatformTransactionManager transactionManager;
	@Autowired private DeletaEnderecoTravessaOuPracaTasklet tasklet;
	
	@Bean
	Step step01DeletaEnderecoTravessaOuPraca() {
		return new StepBuilder("step01DeletaEnderecoTravessaOuPraca", jobRepository)//
				.tasklet(tasklet, transactionManager)//
				.build();//
	}
}