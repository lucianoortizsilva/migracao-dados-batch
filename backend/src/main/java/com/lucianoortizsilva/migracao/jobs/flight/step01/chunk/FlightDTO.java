package com.lucianoortizsilva.migracao.jobs.flight.step01.chunk;

public record FlightDTO(String id, String flightDate, String startingAirport, String destinationAirport, String travelDuration, Boolean isBasicEconomy, String segmentsAirlineName, String segmentsEquipmentDescription) {}
