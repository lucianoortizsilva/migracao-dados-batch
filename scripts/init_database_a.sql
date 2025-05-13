DROP TABLE IF EXISTS flight_economic;

CREATE TABLE flight_economic (
    id VARCHAR(33),
    flightDate TEXT,
	startingAirport TEXT,
	destinationAirport TEXT,
	segmentsAirlineName TEXT,
    PRIMARY KEY (id)
);