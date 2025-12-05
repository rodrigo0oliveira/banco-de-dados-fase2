CREATE TABLE log_finalizacao_consulta (
                                          id SERIAL PRIMARY KEY,
                                          consulta_id INT NOT NULL,
                                          data_finalizacao TIMESTAMP NOT NULL DEFAULT NOW(),
                                          CONSTRAINT fk_log_consulta
                                              FOREIGN KEY (consulta_id) REFERENCES consulta(id) ON DELETE CASCADE
);
