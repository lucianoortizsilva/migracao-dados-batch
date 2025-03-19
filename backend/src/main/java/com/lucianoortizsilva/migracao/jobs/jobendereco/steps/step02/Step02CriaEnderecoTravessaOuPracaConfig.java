package com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step02;

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
public class Step02CriaEnderecoTravessaOuPracaConfig {
	
	@Autowired private JobRepository jobRepository;
	@Autowired private PlatformTransactionManager transactionManager;
	
	@Bean
	Step step02CriaEnderecoTravessaOuPraca(final ItemReader<EnderecoVO> leituraArquivoEnderecoReader, final ClassifierCompositeItemWriter<EnderecoVO> classificacaoCompostaEnderecoWriter) {
		return new StepBuilder("step02CriaEnderecoTravessaOuPraca", jobRepository)//
				.<EnderecoVO, EnderecoVO> chunk(1, transactionManager)//
				.reader(leituraArquivoEnderecoReader)//
				.writer(classificacaoCompostaEnderecoWriter)//
				.build();//
	}
}