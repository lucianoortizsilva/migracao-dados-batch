package com.lucianoortizsilva.migracao.entrypoint.dto;

import java.util.List;

public record JobSummaryDTO(int id, String name, boolean situation, List<StepDTO> steps) {}