package com.lucianoortizsilva.migration.jobs.address;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobAddressConfig {
	
	@Autowired private JobRepository jobRepository;
	@Autowired private Step step01DeleteAddress;
	@Autowired private Step step02CreateAddress;
	
	@Bean
	Job jobAddress() {
		return new JobBuilder("jobAddress", jobRepository)//
				.start(step01DeleteAddress)//
				.next(step02CreateAddress)//
				.incrementer(new RunIdIncrementer())//
				.build();//
	}
}