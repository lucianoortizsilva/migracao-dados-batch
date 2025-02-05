package com.lucianoortizsilva.migracao.steps.step03.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import com.lucianoortizsilva.migracao.model.BonusFuncionario;
import com.lucianoortizsilva.migracao.model.NivelFuncionario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CalculoBonusFuncionarioProcessor implements ItemProcessor<NivelFuncionario, BonusFuncionario> {
	
	@Override
	public BonusFuncionario process(final NivelFuncionario nivel) throws Exception {
		final BonusFuncionario bonus = new BonusFuncionario();
		bonus.setEmail(nivel.getEmail());
		bonus.setValor(calcularBonus(nivel));
		log.info("PROCESSOR > Bônus de R${} para o funcionário com email:{}", bonus.getValor(), bonus.getEmail());
		return bonus;
	}
	
	private static Double calcularBonus(final NivelFuncionario nivel) {
		if (nivel.getCategoria() == "ESPECIALISTA") {
			return 5000.0;
		} else if (nivel.getCategoria() == "SENIOR") {
			return 3000.0;
		} else if (nivel.getCategoria() == "PLENO") {
			return 1000.0;
		}
		return 500.00;
	}
}