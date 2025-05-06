package com.lucianoortizsilva.migracao.entrypoint.amqp;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Payload {
	
	@NotNull private String name;
}