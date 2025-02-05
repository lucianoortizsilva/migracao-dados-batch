package com.lucianoortizsilva.migracao.steps.step02.reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import com.lucianoortizsilva.migracao.model.Funcionario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FuncionarioReader {
	
	@Bean
	@StepScope
	JdbcCursorItemReader<Funcionario> buscarFuncionariosReader(@Qualifier("databaseADataSource") final DataSource databaseEntradaDataSource) {
		return new JdbcCursorItemReaderBuilder<Funcionario>()//
				.name("buscarFuncionariosReader")//
				.dataSource(databaseEntradaDataSource)//
				.sql("select * from funcionario")//
				.rowMapper(rowMapperCliente())//
				.build();//
	}
	
	private static RowMapper<Funcionario> rowMapperCliente() {
		return new RowMapper<>() {
			
			@Override
			public Funcionario mapRow(final ResultSet rs, final int rowNum) throws SQLException {
				final Funcionario funcionario = new Funcionario();
				funcionario.setNome(rs.getString("nome"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setSalario(rs.getDouble("salario"));
				funcionario.setIdade(rs.getInt("idade"));
				log.info("FUNCIONARIO: {}", funcionario.toString());
				return funcionario;
			}
		};
	}
}