package com.lucianoortizsilva.migracao.steps.step01;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.lucianoortizsilva.migracao.config.StepListener;
import com.lucianoortizsilva.migracao.steps.step01.tasklet.DeleteFuncionarioNaoAponsetadoTasklet;

@Configuration
public class Step01Config {
	
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;
	
	public Step01Config(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}
	
	@Bean
	Step step01(final DeleteFuncionarioNaoAponsetadoTasklet deleteFuncionarioNaoAponsetadoTasklet) {
		return new StepBuilder("step01", jobRepository)//
				.tasklet(deleteFuncionarioNaoAponsetadoTasklet, transactionManager)//
				.listener(new StepListener())//
				.build();//
	}
}