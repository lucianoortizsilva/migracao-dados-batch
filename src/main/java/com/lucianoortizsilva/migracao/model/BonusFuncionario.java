package com.lucianoortizsilva.migracao.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BonusFuncionario implements Serializable {
	
	private static final long serialVersionUID = -5198821013598056724L;
	private String nome;
	private String email;
	private Double valor;
}