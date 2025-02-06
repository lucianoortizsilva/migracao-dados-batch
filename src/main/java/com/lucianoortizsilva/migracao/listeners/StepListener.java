package com.lucianoortizsilva.migracao.listeners;

import org.slf4j.MDC;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepListener implements StepExecutionListener {
	
	@Override
	public void beforeStep(final StepExecution stepExecution) {
		MDC.put("stepName", stepExecution.getStepName());
	}
	
	@Override
	public ExitStatus afterStep(final StepExecution stepExecution) {
		MDC.remove("stepName");
		return stepExecution.getExitStatus();
	}
}