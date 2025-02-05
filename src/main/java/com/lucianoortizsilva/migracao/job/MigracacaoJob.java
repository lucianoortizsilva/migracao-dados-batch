package com.lucianoortizsilva.migracao.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class MigracacaoJob {
	
	private final JobRepository jobRepository;
	
	public MigracacaoJob(final JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	@Bean
	Job migrarDadosFuncionariosJob(//
			@Qualifier("step01") final Step step01, //
			@Qualifier("step02") final Step step02, //
			@Qualifier("step03") final Step step03//
	) {
		return new JobBuilder("migrarDadosFuncionariosJob", jobRepository)//
				.start(step01)//
				.next(parallelSteps(step02, step03))//
				.next(step03).incrementer(new RunIdIncrementer())//
				.build();//
	}
	
	@SuppressWarnings("resource")
	private Step parallelSteps(final Step step02, final Step step03) {
		final Flow step02Flow = new FlowBuilder<Flow>("step02Flow").start(step02).build();
		final Flow step03Flow = new FlowBuilder<Flow>("step03Flow").start(step03).build();
		final Flow parallelFlows = new FlowBuilder<Flow>("parallelStepsFlow").split(new SimpleAsyncTaskExecutor()).add(step02Flow, step03Flow).build();
		return new StepBuilder("parallelStepsStep", jobRepository).flow(parallelFlows).build();
	}
}