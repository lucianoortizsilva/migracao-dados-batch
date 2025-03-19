DROP TABLE IF EXISTS logradouro_travessa;

DROP TABLE IF EXISTS logradouro_praca;

CREATE TABLE logradouro_travessa (
    cep VARCHAR(8),
    logradouro TEXT,
    PRIMARY KEY (cep)
);

CREATE TABLE logradouro_praca (
    cep VARCHAR(8),
    logradouro TEXT,
    PRIMARY KEY (cep)
);
