package com.lucianoortizsilva.migracao.steps.step03;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.lucianoortizsilva.migracao.config.StepListener;
import com.lucianoortizsilva.migracao.model.BonusFuncionario;
import com.lucianoortizsilva.migracao.model.NivelFuncionario;

@Configuration
public class Step03Config {
	
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;
	
	public Step03Config(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}
	
	@Bean
	Step step03(//
			final ItemReader<NivelFuncionario> buscarNivelFuncionariosNoArquivoReader, //
			final ItemProcessor<NivelFuncionario, BonusFuncionario> calculoBonusFuncionarioProcessor, //
			final FlatFileItemWriter<BonusFuncionario> escreverNoArquivoBonusFuncionarioWriter//
	) {//
		return new StepBuilder("step03", jobRepository)//
				.listener(new StepListener())//
				.<NivelFuncionario, BonusFuncionario> chunk(5, transactionManager)//
				.reader(buscarNivelFuncionariosNoArquivoReader)//
				.processor(calculoBonusFuncionarioProcessor)//
				.writer(escreverNoArquivoBonusFuncionarioWriter)//
				.stream(escreverNoArquivoBonusFuncionarioWriter)//
				.build();//
	}
}