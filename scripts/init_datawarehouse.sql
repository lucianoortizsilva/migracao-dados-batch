DROP TABLE IF EXISTS street_square;

DROP TABLE IF EXISTS street_not_square;

CREATE TABLE street_square (
    cep VARCHAR(8),
    logradouro TEXT,
    PRIMARY KEY (cep)
);

CREATE TABLE street_not_square (
    cep VARCHAR(8),
    logradouro TEXT,
    PRIMARY KEY (cep)
);
