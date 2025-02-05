package com.lucianoortizsilva.migracao.steps.step01.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeleteFuncionarioNaoAponsetadoTasklet implements Tasklet {
	
	private final JdbcTemplate jdbcTemplate;
	
	public DeleteFuncionarioNaoAponsetadoTasklet(@Qualifier("JdbcTemplateBDataSource") final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
		final String sql = "DELETE FROM funcionario_nao_aposentado";
		final int rowsDeleted = jdbcTemplate.update(sql);
		System.out.println("Deleted " + rowsDeleted + " rows from funcionario");
		return RepeatStatus.FINISHED;
	}
}