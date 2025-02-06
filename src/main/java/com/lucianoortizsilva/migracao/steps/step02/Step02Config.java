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
import com.lucianoortizsilva.migracao.listeners.StepListener;
import com.lucianoortizsilva.migracao.model.Funcionario;

/**
 * Esse 'Step02' é responsável por:
 * - 1º READER > Ler dados da tabela funcionario;
 * - 2º WRITER :
 *  > Escrever alguns dados da leitura em: files/arquivos-saida/funcionario_aposentados.csv
 *  OU
 *  > Escrever alguns dados da leitura em: tabela funcionario_nao_aposentado (PostgreSQL)
 */
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
