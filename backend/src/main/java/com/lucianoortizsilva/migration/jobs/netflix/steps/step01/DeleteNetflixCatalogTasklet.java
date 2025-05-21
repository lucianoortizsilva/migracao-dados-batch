package com.lucianoortizsilva.migration.jobs.netflix.steps.step01;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeleteNetflixCatalogTasklet implements Tasklet {
	
	@Autowired private JdbcTemplate JdbcTemplateDatalakeSource;
	
	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
		JdbcTemplateDatalakeSource.execute("DELETE FROM netflix_catalog");
		return RepeatStatus.FINISHED;
	}
}