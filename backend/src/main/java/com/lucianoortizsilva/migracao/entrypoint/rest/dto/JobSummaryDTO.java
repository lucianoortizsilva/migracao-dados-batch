package com.lucianoortizsilva.migracao.entrypoint.rest.dto;

import java.util.List;

public record JobSummaryDTO(int id, String name, boolean situation, List<StepDTO> steps) {}