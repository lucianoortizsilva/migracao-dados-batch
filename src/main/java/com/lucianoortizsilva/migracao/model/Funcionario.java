package com.lucianoortizsilva.migracao.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Funcionario implements Serializable {
	
	private static final long serialVersionUID = 599696200052840851L;
	private String nome;
	private Integer idade;
	private String email;
	private Double salario;
}