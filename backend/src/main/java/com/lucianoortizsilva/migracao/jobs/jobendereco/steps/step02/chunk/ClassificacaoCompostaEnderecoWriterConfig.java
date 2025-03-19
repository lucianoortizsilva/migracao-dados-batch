package com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step02.chunk;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step02.EnderecoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ClassificacaoCompostaEnderecoWriterConfig {
	
	@Bean
	ClassifierCompositeItemWriter<EnderecoVO> classificacaoCompostaEnderecoWriter(final JdbcBatchItemWriter<EnderecoVO> escreveLogradouroTravessaJdbcWriter, final JdbcBatchItemWriter<EnderecoVO> escreveLogradouroPracaJdbcWriter) {
		return new ClassifierCompositeItemWriterBuilder<EnderecoVO>()//
				.classifier(validarOndeVaiEscrever(escreveLogradouroTravessaJdbcWriter, escreveLogradouroPracaJdbcWriter))//
				.build();//
	}
	
	private static Classifier<EnderecoVO, ItemWriter<? super EnderecoVO>> validarOndeVaiEscrever(final JdbcBatchItemWriter<EnderecoVO> escreveLogradouroTravessaJdbcWriter, final JdbcBatchItemWriter<EnderecoVO> escreveLogradouroPracaJdbcWriter) {
		return new Classifier<>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public ItemWriter<? super EnderecoVO> classify(final EnderecoVO endereco) {
				final var logradouro = endereco.logradouro().toUpperCase();
				if (logradouro.startsWith("TRAVESSA")) {
					log.info("ENCONTROU TRAVESSA :{}", logradouro);
					return escreveLogradouroTravessaJdbcWriter;
				} else if (logradouro.startsWith("PRAÇA")) {
					log.info("ENCONTROU PRAÇA :{}", logradouro);
					return escreveLogradouroPracaJdbcWriter;
				} else {
					return item -> log.warn("ENDEREÇO NÃO CLASSIFICADO: {}", logradouro);
				}
			}
		};
	}
}