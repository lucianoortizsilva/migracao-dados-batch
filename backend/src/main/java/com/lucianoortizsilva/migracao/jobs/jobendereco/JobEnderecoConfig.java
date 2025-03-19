package com.lucianoortizsilva.migracao.jobs.jobendereco;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobEnderecoConfig {
	
	@Autowired private JobRepository jobRepository;
	@Autowired private Step step01DeletaEnderecoTravessaOuPraca;
	@Autowired private Step step02CriaEnderecoTravessaOuPraca;
	
	@Bean
	Job jobEndereco() {
		return new JobBuilder("jobEndereco", jobRepository)//
				.start(step01DeletaEnderecoTravessaOuPraca)//
				.next(step02CriaEnderecoTravessaOuPraca)//
				.incrementer(new RunIdIncrementer())//
				.build();//
	}
}