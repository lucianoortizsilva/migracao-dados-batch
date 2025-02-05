package com.lucianoortizsilva.migracao.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NivelFuncionario implements Serializable {
	
	private static final long serialVersionUID = -1146070616792460992L;
	private String categoria;
	private String email;
}