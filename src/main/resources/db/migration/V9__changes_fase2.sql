ALTER TABLE pagamento_consulta
    ADD COLUMN paciente_id int,
ADD CONSTRAINT fk_pagamento_paciente FOREIGN KEY (paciente_id)
REFERENCES paciente(id);

ALTER TABLE medico
    ALTER COLUMN endereco_id SET NOT NULL;

ALTER TABLE paciente
    ALTER COLUMN endereco_id SET NOT NULL;