DROP TABLE IF EXISTS funcionario_nao_aposentado;

CREATE TABLE funcionario_nao_aposentado (
    nome TEXT,
    idade INT,
    email VARCHAR(50),
    salario DOUBLE PRECISION,
    PRIMARY KEY (email)
);