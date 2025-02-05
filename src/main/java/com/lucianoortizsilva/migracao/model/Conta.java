package com.lucianoortizsilva.migracao.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Conta implements Serializable {
	
	private static final long serialVersionUID = -1146070616792460992L;
	private String categoria;
	private Double saldo;
	private String email;
}