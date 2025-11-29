CREATE TABLE medico_especialidade (
                                      medico_id int NOT NULL,
                                      especialidade_id int NOT NULL,
                                      CONSTRAINT pk_medico_especialidade PRIMARY KEY (medico_id, especialidade_id),
                                      CONSTRAINT fk_mes_medico FOREIGN KEY (medico_id) REFERENCES medico(id),
                                      CONSTRAINT fk_mes_especialidade FOREIGN KEY (especialidade_id) REFERENCES especialidade(id)
);
