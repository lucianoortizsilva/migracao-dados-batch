package com.lucianoortizsilva.migracao.jobs.address.steps.step02.chunk;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lucianoortizsilva.migracao.jobs.address.steps.step02.AddressVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ClassificationCompositeAddressWriterConfig {
	
	@Bean
	ClassifierCompositeItemWriter<AddressVO> classificationCompositeAddressWriter(final JdbcBatchItemWriter<AddressVO> streetSquareJdbcWriter, final JdbcBatchItemWriter<AddressVO> streetNotSquareJdbcWriter) {
		return new ClassifierCompositeItemWriterBuilder<AddressVO>()//
				.classifier(validarOndeVaiEscrever(streetSquareJdbcWriter, streetNotSquareJdbcWriter))//
				.build();//
	}
	
	private static Classifier<AddressVO, ItemWriter<? super AddressVO>> validarOndeVaiEscrever(final JdbcBatchItemWriter<AddressVO> streetSquareJdbcWriter, final JdbcBatchItemWriter<AddressVO> streetNotSquareJdbcWriter) {
		return new Classifier<>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public ItemWriter<? super AddressVO> classify(final AddressVO vo) {
				final var street = vo.logradouro().toUpperCase();
				if (street.startsWith("PRAÇA")) {
					log.info("it found street square :{}", street);
					return streetSquareJdbcWriter;
				}
				log.info("it not found street square :{}", street);
				return streetNotSquareJdbcWriter;
			}
		};
	}
}