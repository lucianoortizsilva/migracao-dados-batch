package com.lucianoortizsilva.migracao.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigracacaoJob {
	
	private final JobRepository jobRepository;
	
	public MigracacaoJob(final JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	@Bean
	Job migrarDadosFuncionariosJob(@Qualifier("deletarFuncionarioNaoAposentadoStep") final Step deletarFuncionarioNaoAposentadoStep, @Qualifier("migrarFuncionariosStep") final Step migrarFuncionariosStep) {
		return new JobBuilder("migrarDadosFuncionariosJob", jobRepository)//
				.start(deletarFuncionarioNaoAposentadoStep)//
				.next(migrarFuncionariosStep)//
				.incrementer(new RunIdIncrementer())//
				.build();//
	}
}
