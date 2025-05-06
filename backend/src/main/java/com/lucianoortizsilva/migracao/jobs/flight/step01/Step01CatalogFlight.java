package com.lucianoortizsilva.migracao.jobs.flight.step01;

import java.util.HashMap;
import java.util.Map;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import com.lucianoortizsilva.migracao.jobs.flight.step01.chunk.FlightDTO;

@Configuration
public class Step01CatalogFlight {
	
	@Autowired private JobRepository jobRepository;
	@Autowired private PlatformTransactionManager transactionManager;
	private final static int FIFTY_THOUSAND = 50000;
	
	@Bean
	Step step01CatalogFlightManager() throws Exception {
		return new StepBuilder("step01CatalogFlightManager", jobRepository)//
				.partitioner("step01CatalogFlightSlave", partitioner())//
				.partitionHandler(partitionHandlerX(null))//
				.build();//
	}
	
	@Bean
	Step step01CatalogFlightSlave(final FlatFileItemReader<FlightDTO> reader) {
		return new StepBuilder("step01CatalogFlightSlave", jobRepository)//
				.<FlightDTO, FlightDTO> chunk(10, transactionManager)//
				.reader(reader).writer(items -> System.out.println(Thread.currentThread().getName() + " - Write: " + items))//
				.build();//
	}
	
	@Bean
	Partitioner partitioner() {
		return gridSize -> {
			final Map<String, ExecutionContext> partitionMap = new HashMap<>();
			final int range = FIFTY_THOUSAND / 4;
			final int remainder = FIFTY_THOUSAND % 4;
			int start = 0;
			for (int i = 0; i < 4; i++) {
				int end = start + range - 1;
				if (i < remainder) {
					end += 1;
				}
				final ExecutionContext ctx = new ExecutionContext();
				ctx.putInt("startLine", start);
				ctx.putInt("endLine", end);
				partitionMap.put("partition" + i, ctx);
				start = end + 1;
			}
			return partitionMap;
		};
	}
	
	@Bean
	@StepScope
	FlatFileItemReader<FlightDTO> reader(@Value("#{stepExecutionContext['startLine']}") final Integer startLine, @Value("#{stepExecutionContext['endLine']}") final Integer endLine) {
		final int linesToRead = endLine - startLine + 1;
		final FlatFileItemReader<FlightDTO> reader = new FlatFileItemReader<>() {
			
			private int linesRead = 0;
			
			@Override
			protected FlightDTO doRead() throws Exception {
				if (linesRead >= linesToRead) {
					return null;
				}
				final FlightDTO pessoa = super.doRead();
				if (pessoa != null) {
					linesRead++;
				}
				return pessoa;
			}
		};
		reader.setResource(new FileSystemResource("files/in/itineraries.csv"));
		final DefaultLineMapper<FlightDTO> mapper = new DefaultLineMapper<>();
		final DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames("id", "flightDate", "startingAirport", "destinationAirport", "travelDuration", "isBasicEconomy", "segmentsAirlineName", "segmentsEquipmentDescription");
		mapper.setLineTokenizer(tokenizer);
		mapper.setFieldSetMapper(fieldSet -> new FlightDTO(//
				fieldSet.readString("id"), //
				fieldSet.readString("flightDate"), //
				fieldSet.readString("startingAirport"), //
				fieldSet.readString("destinationAirport"), //
				fieldSet.readString("travelDuration"), //
				fieldSet.readBoolean("isBasicEconomy"), //
				fieldSet.readString("segmentsAirlineName"), //
				fieldSet.readString("segmentsEquipmentDescription")));//
		reader.setLineMapper(mapper);
		return reader;
	}
	
	@Bean
	TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor("partition_executor");
	}
	
	@Bean
	PartitionHandler partitionHandlerX(final Step step01CatalogFlightSlave) throws Exception {
		final TaskExecutorPartitionHandler taskExecutorPartitionHandler = new TaskExecutorPartitionHandler();
		taskExecutorPartitionHandler.setTaskExecutor(taskExecutor());
		taskExecutorPartitionHandler.setStep(step01CatalogFlightSlave);
		return taskExecutorPartitionHandler;
	}
}