package com.lucianoortizsilva.migracao.steps.step02.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lucianoortizsilva.migracao.model.Funcionario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ClassifierCompositeItemWriterConfig {
	
	@Bean
	ClassifierCompositeItemWriter<Funcionario> funcionarioClassifierCompositeItemWriter(final FlatFileItemWriter<Funcionario> escreverFuncionarioAposentadoWriter, final JdbcBatchItemWriter<Funcionario> escreverFuncionarioNaoAposentadoWriter) {
		return new ClassifierCompositeItemWriterBuilder<Funcionario>()//
				.classifier(validarOndeVaiEscrever(escreverFuncionarioAposentadoWriter, escreverFuncionarioNaoAposentadoWriter))//
				.build();//
	}
	
	private static Classifier<Funcionario, ItemWriter<? super Funcionario>> validarOndeVaiEscrever(final FlatFileItemWriter<Funcionario> escreverFuncionarioAposentadoWriter, final JdbcBatchItemWriter<Funcionario> escreverFuncionarioNaoAposentadoWriter) {
		return new Classifier<>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public ItemWriter<? super Funcionario> classify(final Funcionario funcionario) {
				if (funcionario.getIdade().intValue() > 65) {
					log.info("WRITER > Funcionario APOSENTADO: {}", funcionario.getNome());
					return escreverFuncionarioAposentadoWriter;
				}
				log.info("WRITER > Funcionario TRABALHANDO: {}", funcionario.getNome());
				return escreverFuncionarioNaoAposentadoWriter;
			}
		};
	}
}