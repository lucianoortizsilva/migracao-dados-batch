package com.lucianoortizsilva.migracao.jobs.jobendereco.steps.step01.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class DeletaEnderecoTravessaOuPracaTasklet implements Tasklet {
	
	@Autowired private JdbcTemplate JdbcTemplateBDataSource;
	
	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
		JdbcTemplateBDataSource.execute("DELETE FROM logradouro_travessa");
		JdbcTemplateBDataSource.execute("DELETE FROM logradouro_praca");
		return RepeatStatus.FINISHED;
	}
}