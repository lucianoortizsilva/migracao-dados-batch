package com.lucianoortizsilva.migracao.steps.step02;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.lucianoortizsilva.migracao.config.StepListener;
import com.lucianoortizsilva.migracao.model.Funcionario;

@Configuration
public class Step02Config {
	
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;
	
	public Step02Config(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}
	
	@Bean
	Step step02(//
			final ItemReader<Funcionario> buscarFuncionariosReader, //
			final ClassifierCompositeItemWriter<Funcionario> funcionarioClassifierCompositeItemWriter, //
			final FlatFileItemWriter<Funcionario> escreverFuncionarioAposentadoWriter//
	) {//
		return new StepBuilder("step02", jobRepository)//
				.<Funcionario, Funcionario> chunk(5, transactionManager)//
				.listener(new StepListener())//
				.reader(buscarFuncionariosReader)//
				.writer(funcionarioClassifierCompositeItemWriter)//
				.stream(escreverFuncionarioAposentadoWriter)//
				.build();//
	}
}
